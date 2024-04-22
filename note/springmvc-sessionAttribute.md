https://www.cnblogs.com/winclpt/articles/7212636.html
https://mp.weixin.qq.com/s?__biz=MzI1NDY0MTkzNQ==&mid=2247492725&idx=1&sn=502747761ef7786ca1ccc7d6a8970670&scene=21#wechat_redirect

#### session attribute相关的注解

###### @SessionAttribute 

> 和@RequestAttribute类似，它的作用是：取出预先在session中设置好的数据，比如拦截器中。


###### @SessionAttributes

> @SessionAttributes 作用于处理器类上，这个注解可以把参数存储到 session 中，进而可以实现在多个请求之间传递参数。

> @SessionAttributes 的作用类似于 Session 的 Attribute 属性，但不完全一样，一般来说 @SessionAttributes 设置的参数只用于临时的参数传递，而不是长期的保存，参数用完之后可以通过 SessionStatus 将之清除。

> 通过 @SessionAttributes 注解设置的参数我们可以在三个地方获取：
> 1. 在当前的视图中直接通过 request.getAttribute 或 session.getAttribute 获取。
> 2. 在后面的请求中，也可以通过 session.getAttribute 获取。重定向的参数传递问题也可以这样解决
> 3. 在后续的请求中，也可以直接从 Model 中获取。