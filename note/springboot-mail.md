参考： https://wangsong.blog.csdn.net/article/details/96269046



邮件基础
我们经常会听到各种各样的邮件协议，比如 SMTP、POP3、IMAP ，那么这些协议有什么作用，有什么区别？我们先来讨论一下这个问题。

SMTP 是一个基于 TCP/IP 的应用层协议，江湖地位有点类似于 HTTP，SMTP 服务器默认监听的端口号为 25 。看到这里，小伙伴们可能会想到既然 SMTP 协议是基于 TCP/IP 的应用层协议，那么我是不是也可以通过 Socket 发送一封邮件呢？回答是肯定的。

生活中我们投递一封邮件要经过如下几个步骤：

深圳的小王先将邮件投递到深圳的邮局
深圳的邮局将邮件运送到上海的邮局
上海的小张来邮局取邮件
这是一个缩减版的生活中邮件发送过程。这三个步骤可以分别对应我们的邮件发送过程，假设从 aaa@qq.com 发送邮件到 111@163.com ：

aaa@qq.com 先将邮件投递到腾讯的邮件服务器
腾讯的邮件服务器将我们的邮件投递到网易的邮件服务器
111@163.com 登录网易的邮件服务器查看邮件
邮件投递大致就是这个过程，这个过程就涉及到了多个协议，我们来分别看一下。

SMTP 协议全称为 Simple Mail Transfer Protocol，译作简单邮件传输协议，它定义了邮件客户端软件与 SMTP 服务器之间，以及 SMTP 服务器与 SMTP 服务器之间的通信规则。

也就是说 aaa@qq.com 用户先将邮件投递到腾讯的 SMTP 服务器这个过程就使用了 SMTP 协议，然后腾讯的 SMTP 服务器将邮件投递到网易的 SMTP 服务器这个过程也依然使用了 SMTP 协议，SMTP 服务器就是用来收邮件。

而 POP3 协议全称为 Post Office Protocol ，译作邮局协议，它定义了邮件客户端与 POP3 服务器之间的通信规则，那么该协议在什么场景下会用到呢？当邮件到达网易的 SMTP 服务器之后， 111@163.com 用户需要登录服务器查看邮件，这个时候就该协议就用上了：邮件服务商都会为每一个用户提供专门的邮件存储空间，SMTP 服务器收到邮件之后，就将邮件保存到相应用户的邮件存储空间中，如果用户要读取邮件，就需要通过邮件服务商的 POP3 邮件服务器来完成。

最后，可能也有小伙伴们听说过 IMAP 协议，这个协议是对 POP3 协议的扩展，功能更强，作用类似，这里不再赘述。
————————————————

                            版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。

原文链接：https://blog.csdn.net/u012702547/article/details/96269046