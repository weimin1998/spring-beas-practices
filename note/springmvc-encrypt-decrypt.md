##### springmvc加解密请求响应(ResponseBodyAdvice 和 RequestBodyAdvice)

> 参考：https://blog.csdn.net/u012702547/article/details/114581734

###### 1.定义两个注解

> 这两个注解就是两个标记，在以后使用的过程中，哪个接口方法添加了 @Encrypt 注解就对哪个接口的数据加密返回，<br>
> 哪个接口/参数添加了 @Decrypt 注解就对哪个接口/参数进行解密。<br>
> 需要注意的是 @Decrypt 比 @Encrypt 多了一个使用场景就是 @Decrypt 可以用在参数上。<br>

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD,ElementType.PARAMETER})
    public @interface Decrypt {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Encrypt {
    }


###### 2.配置加密需要的key

> 考虑到用户可能会自己配置加密的 key，因此我们再来定义一个 EncryptProperties 类来读取用户配置的 key

    @ConfigurationProperties(prefix = "spring.encrypt")
    public class EncryptProperties {
        private final static String DEFAULT_KEY = "weimin1234567890";
        private String key = DEFAULT_KEY;
    
        public String getKey() {
            return key;
        }
    
        public void setKey(String key) {
            this.key = key;
        }
    }

###### 3.ResponseBodyAdvice 和 RequestBodyAdvice

> RequestBodyAdvice 在做解密的时候倒是没啥问题，而 ResponseBodyAdvice 在做加密的时候则会有一些局限<br>
> ResponseBodyAdvice 在使用了 @ResponseBody 注解的时候才会生效，RequestBodyAdvice 在使用了 @RequestBody 注解的时候才会生效。<br>
> 换言之，前后端都是 JSON 交互的时候，这两个才有用。不过一般来说接口加解密的场景也都是前后端分离的时候才可能有的事。<br>


###### 4.加密响应

>接口方法满足下列要求，response才会被加密<br>
> 1.接口方法的返回类型是 指定的泛型时，比如RespBean;<br>
> 2.接口方法上标注了Encrypt注解

> 1.supports：这个方法用来判断什么样的接口需要加密，参数 returnType 表示返回类型，我们这里的判断逻辑就是方法是否含有 @Encrypt 注解，如果有，表示该接口需要加密处理，如果没有，表示该接口不需要加密处理。<br>
> 2.beforeBodyWrite：这个方法会在数据响应之前执行，也就是我们先对响应数据进行二次处理，处理完成后，才会转成 json 返回。我们这里的处理方式很简单，RespBean 中的 status 是状态码就不用加密了，另外两个字段重新加密后重新设置值即可。<br>
> 3.另外需要注意，自定义的 ResponseBodyAdvice 需要用 @ControllerAdvice 注解来标记。<br>

    @EnableConfigurationProperties(EncryptProperties.class)
    @ControllerAdvice
    // 这里的泛型，表示当接口的返回类型是该泛型时，才会加密
    public class EncryptResponse implements ResponseBodyAdvice<RespBean> {
        private static final Logger logger = LoggerFactory.getLogger(EncryptResponse.class);
        private final ObjectMapper om = new ObjectMapper();
    
        @Autowired
        EncryptProperties encryptProperties;
    
        @Override
        public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
            // 接口方法上标注了 Encrypt 注解才加密
            return returnType.hasMethodAnnotation(Encrypt.class);
        }
    
        @Override
        public RespBean beforeBodyWrite(RespBean body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
            try {
                if (body.getMsg() != null) {
                    body.setMsg(AESUtil.encrypt(body.getMsg().getBytes(), encryptProperties.getKey().getBytes()));
                }
                if (body.getObj() != null) {
                    body.setObj(AESUtil.encrypt(om.writeValueAsBytes(body.getObj()), encryptProperties.getKey().getBytes()));
                }
            } catch (Exception e) {
                logger.error("加密失败！" + e.getMessage());
            }
            return body;
        }
    }

> 加密后的示例：

    {
        "status": 200,
        "msg": "TU9vsZNE8/UP8dgrQCMFIQ==",
        "obj": "XI/Ze5j/B4A2P9V+df5KBZr6t3j7zySPXqs1rkJXXKY="
    }

###### 5.解密请求

> 1.首先，DecryptRequest 类没有直接实现 RequestBodyAdvice 接口，而是继承自 RequestBodyAdviceAdapter 类，
> 该类是 RequestBodyAdvice 接口的子类，并且实现了接口中的一些方法，这样当我们继承自 RequestBodyAdviceAdapter 时，就只需要根据自己实际需求实现某几个方法即可。<br>
> 2.supports：该方法用来判断哪些接口需要处理接口解密，这里的判断逻辑是方法上或者参数上含有 @Decrypt 注解的接口，处理解密问题。<br>
> 3.beforeBodyRead：这个方法会在参数转换成具体的对象之前执行，先从流中加载到数据，然后对数据进行解密，解密完成后再重新构造 HttpInputMessage 对象返回。<br>

    @EnableConfigurationProperties(EncryptProperties.class)
    @ControllerAdvice
    public class DecryptRequest extends RequestBodyAdviceAdapter {
        private static final Logger logger = LoggerFactory.getLogger(DecryptRequest.class);
    
        @Autowired
        EncryptProperties encryptProperties;
    
        @Override
        public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
            // 接口方法上 或者方法参数上 标注了 Decrypt 注解才解密
            return methodParameter.hasMethodAnnotation(Decrypt.class) || methodParameter.hasParameterAnnotation(Decrypt.class);
        }
    
        // 这个方法会在参数转换成具体的对象之前执行，我们先从流中加载到数据，然后对数据进行解密，解密完成后再重新构造 HttpInputMessage 对象返回。
        @Override
        public HttpInputMessage beforeBodyRead(final HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
            // 先从流中加载到数据
            byte[] body = new byte[inputMessage.getBody().available()];
            int read = inputMessage.getBody().read(body);
            logger.info("读到请求数据长度：" + read);
    
            try {
                // 然后对数据进行解密
                byte[] decrypt = AESUtil.decrypt(body, encryptProperties.getKey().getBytes());
                final ByteArrayInputStream bais = new ByteArrayInputStream(decrypt);
    
                // 解密完成后再重新构造 HttpInputMessage 对象返回
                return new HttpInputMessage() {
                    @Override
                    public InputStream getBody() throws IOException {
                        return bais;
                    }
    
                    @Override
                    public HttpHeaders getHeaders() {
                        return inputMessage.getHeaders();
                    }
                };
            } catch (Exception e) {
                logger.error("解密失败！" + e.getMessage());
            }
            return super.beforeBodyRead(inputMessage, parameter, targetType, converterType);
        }
    }
