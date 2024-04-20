###### spring mvc flashmap的使用
> 参考： https://blog.csdn.net/u012702547/article/details/114302516

> 试想这样一个场景：<br>
> 在一个电商项目中，有一个提交订单的请求，这个请求是一个 POST 请求，请求参数都在请求体中。<br>
> 当用户提交成功后，为了防止用户刷新浏览器页面造成订单请求重复提交，我们一般会将用户重定向到一个显示订单的页面，这样即使用户刷新页面，也不会造成订单请求重复提交。

    @Controller
    public class OrderController {
    @PostMapping("/order")
    public String order(OrderInfo orderInfo) {
        //其他处理逻辑
        return "redirect:/orderlist";
        }
    }

> 但是这里有一个问题：如果想传递参数怎么办？<br>

> 如果是服务器端跳转，我们可以将参数放在 request
> 对象中，跳转完成后还能拿到参数，但是如果是客户端跳转我们就只能将参数放在地址栏中了，像上面这个方法的返回值我们可以写成：return "redirect:/orderlist?xxx=xxx";<br>
> 这种传参方式有两个缺陷：

> 1.地址栏的长度是有限的，也就意味着能够放在地址栏中的参数是有限的。<br>
> 2.不想将一些特殊的参数放在地址栏中。



> 在重定向时，如果需要传递参数，但是又不想放在地址栏中，我们就可以通过 flashMap 来传递参数



> http://localhost:8080/orderPage