##### @ControllerAdvice

>@ControllerAdvice ，顾名思义，这是一个增强的 Controller。使用这个 Controller ，可以实现三个方面的功能：<br>
> 1. 全局异常处理
> 2. 全局数据绑定
> 3. 全局数据预处理

> 灵活使用这三个功能，可以帮助我们简化很多工作，需要注意的是，这是 SpringMVC 提供的功能，在 Spring Boot 中可以直接使用.

###### 全局异常处理

> 使用 @ControllerAdvice 实现全局异常处理，只需要定义类，添加该注解即可，定义方式如下：
    
    // 全局异常处理，返回视图
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView customNullPointerException(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", e.getMessage());
        mv.setViewName("errorPage");
        return mv;
    }


    // 全局异常处理，返回数据
    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    @ResponseBody
    public RespBean customIndexOutException(Exception e) {
        return RespBean.error(e.getMessage());
    }


    // 全局异常处理
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RespBean customException(Exception e) {
        return RespBean.error(e.getMessage());
    }

> 在该类中，可以定义多个方法，不同的方法处理不同的异常，例如专门处理空指针的方法、专门处理数组越界的方法...，也可以在一个方法中处理所有的异常信息。


###### 全局数据绑定
> 全局数据绑定功能可以用来做一些初始化的数据操作，<br>
> 我们可以将一些公共的数据定义在添加了 @ControllerAdvice 注解的类中，<br>
> 这样，在每一个  Controller 的接口中，就都能够访问导致这些数据。

> 首先定义全局数据，如下：

    @ControllerAdvice
    public class MyGlobalDataBinding {
        // 全局数据
        @ModelAttribute(name = "md")
        public Map<String,Object> mydata() {
            HashMap<String, Object> map = new HashMap<>();
            map.put("age", 99);
            map.put("gender", "男");
            return map;
        }
    }

> 使用 **@ModelAttribute** 注解标记该方法的返回数据是一个全局数据，默认情况下，这个全局数据的 key 就是返回的变量名，value 就是方法返回值，<br>
> 当然开发者可以通过 @ModelAttribute 注解的 name 属性去重新指定 key。

> 定义完成后，在任何一个Controller 的接口中，都可以获取到这里定义的数据：

    // 测试全局数据绑定
    @GetMapping("/mdmap")
    @ResponseBody
    public String hello(Model model) {
        Map<String, Object> map = model.asMap();
        System.out.println(map);
        return "hello controller advice";
    }

> {md={gender=男, age=99}}


###### 全局数据预处理

> 考虑我有两个实体类，Book 和 Author，分别定义如下：

    public class Book {
        private String name;
        private Long price;
    }

    public class Author {
        private String name;
        private Integer age;
    }


> 定义一个接口，如下：
    
    @PostMapping("/book")
    public void addBook(Book book, Author author) {
        System.out.println(book);
        System.out.println(author);
    }

> 因为两个实体类都有一个 name 属性，从前端传递时 ，无法区分。<br>
> http://localhost:8080/book?name=111&price=100&age=20 <br>
> 
> Book{name='111', price=100}<br>
> Author{name='111', age=20}<br>
> book: Book{name='111', price=100}, author: Author{name='111', age=20}


> 解决步骤如下:
> 1. @ModelAttribute(""), 给接口中的变量取别名

    @GetMapping("/book")
    @ResponseBody
    public String addBook(@ModelAttribute("b") Book book, @ModelAttribute("a") Author author) {
        System.out.println(book);
        System.out.println(author);
        return "book: " + book.toString() + ", author: " + author.toString();
    }

> 2. 进行请求数据预处理<br>
>  @InitBinder("b") 注解表示该方法用来处理和Book和相关的参数,在方法中,给参数添加一个 b 前缀,即请求参数要有b前缀.
    
    @ControllerAdvice
    public class MyGlobalDataPreHandler {
        @InitBinder("b")
        public void b(WebDataBinder binder) {
            binder.setFieldDefaultPrefix("b.");
        }
        @InitBinder("a")
        public void a(WebDataBinder binder) {
            binder.setFieldDefaultPrefix("a.");
        }
    }

> http://localhost:8080/book?a.name=%E7%BD%97%E8%B4%AF%E4%B8%AD&a.age=50&b.name=%E4%B8%89%E5%9B%BD%E6%BC%94%E4%B9%89&b.price=100 <br>
> Book{name='三国演义', price=100}<br>
> Author{name='罗贯中', age=50}<br>
> book: Book{name='三国演义', price=100}, author: Author{name='罗贯中', age=50}