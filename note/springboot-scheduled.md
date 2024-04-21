#### 开启定时任务

#### 1.@Scheduled

    @EnableScheduling


    首先使用 @Scheduled 注解开启一个定时任务。
    fixedRate 表示任务执行之间的时间间隔，具体是指两次任务的开始时间间隔，即第二次任务开始时，第一次任务可能还没结束。
    fixedDelay 表示任务执行之间的时间间隔，具体是指本次任务结束到下次任务开始之间的时间间隔。
    initialDelay 表示首次任务启动的延迟时间。
    所有时间的单位都是毫秒。

#### 2.第三方 Quartz
    一般在项目中，除非定时任务涉及到的业务实在是太简单，使用 @Scheduled 注解来解决定时任务，否则大部分情况可能都是使用 Quartz 来做定时任务。
    1.首先引入Quartz依赖
    2.@EnableScheduling
    3.定义任务
    4.配置trigger

    JobDetail 的配置有两种方式：MethodInvokingJobDetailFactoryBean 和 JobDetailFactoryBean 。
    使用 MethodInvokingJobDetailFactoryBean 可以配置目标 Bean 的名字和目标方法的名字，这种方式不支持传参。
    使用 JobDetailFactoryBean 可以配置 JobDetail ，任务类继承自 QuartzJobBean ，这种方式支持传参，将参数封装在 JobDataMap 中进行传递。
    Trigger 是指触发器，Quartz 中定义了多个触发器，这里向大家展示其中两种的用法，SimpleTrigger 和 CronTrigger 。
    SimpleTrigger 有点类似于前面说的 @Scheduled 的基本用法。
    CronTrigger 则有点类似于 @Scheduled 中 cron 表达式的用法。


#### 参考
    https://blog.csdn.net/u012702547/article/details/89376171