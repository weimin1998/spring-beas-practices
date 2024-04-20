package com.example.demo.advice;

import com.example.demo.annotation.Decrypt;
import com.example.demo.config.EncryptProperties;
import com.example.demo.utils.AESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

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
