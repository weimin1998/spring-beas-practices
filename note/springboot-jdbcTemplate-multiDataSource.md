#### jdbcTemplate 多数据源

##### DataSourceConfig

    这里通过 one 和 two 对数据源进行了区分，
    但是加了 one 和 two 之后，这里的配置就没法被 SpringBoot 自动加载了（因为前面的 key 变了），
    需要我们自己去加载 DataSource 了，此时，需要自己配置一个 DataSourceConfig，
    用来提供两个 DataSource Bean

##### JdbcTemplateConfig

    每一个 JdbcTemplate 的创建都需要一个 DataSource，
    由于 Spring 容器中现在存在两个 DataSource，默认使用类型查找，会报错，因此加上 @Qualifier 注解，
    表示按照名称查找。这里创建了两个 JdbcTemplate 实例，分别对应了两个 DataSource。

##### SpringbootJdbcTemplateMultiDataSourceDemoApplicationTests

    和 DataSource 一样，Spring 容器中的 JdbcTemplate 也是有两个，
    因此不能通过 byType 的方式注入进来，
    这里给大伙提供了两种注入思路，一种是使用 @Resource 注解，直接通过 byName 的方式注入进来，
    另外一种就是 @Autowired 注解加上 @Qualifier 注解，两者联合起来，实际上也是 byName。
    将 JdbcTemplate 注入进来之后，jdbcTemplateOne 和 jdbcTemplateTwo 此时就代表操作不同的数据源，
    使用不同的 JdbcTemplate 操作不同的数据源，实现了多数据源配置。

##### 参考：https://mp.weixin.qq.com/s?__biz=MzI1NDY0MTkzNQ==&mid=2247486879&idx=2&sn=16df1ab5688b6df3f4ae59ffa685e00a&chksm=e9c35fffdeb4d6e994d15d66af1d2ab59f491c1dbe99932f4584ed025947ed1ae176b6226720&scene=21#wechat_redirect