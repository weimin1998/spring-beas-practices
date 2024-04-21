将配置文件中的配置绑定到类的属性上

#### 方式1
    类上加@Component 和 @ConfigurationProperties(prefix = "user")

    @Component
    @ConfigurationProperties(prefix = "user")
    public class User {
        String name;
        Integer age;
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public Integer getAge() {
            return age;
        }
    
        public void setAge(Integer age) {
            this.age = age;
        }
    
        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

    }

#### 方式2
    类上加@ConfigurationProperties
    配置类上加@EnableConfigurationProperties(User.class)

    @ConfigurationProperties(prefix = "user")
        public class User {
        String name;
        Integer age;
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public Integer getAge() {
            return age;
        }
    
        public void setAge(Integer age) {
            this.age = age;
        }
    
        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


    @EnableConfigurationProperties(User.class)
    @SpringBootApplication
    public class SpringbootConfigurationPropertiesDemoApplication {

        public static void main(String[] args) {
            ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootConfigurationPropertiesDemoApplication.class, args);
            User bean = applicationContext.getBean(User.class);
            System.out.println(bean);
        }
    }