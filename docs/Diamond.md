# Diamond
Diamond是分布式配置中心，提供配置实时推送服务。Diamond以其卓越的稳定性、易用性、性能、容量每天为集团几乎所有应用服务，为七次双十一保驾护航。

核心功能
+ 使用DiamondOps或Diamond-client发布配置。
+ Diamond-client支持多种方式（主动获取/订阅监听）获取配置。

高级功能
+ 使用DiamondOps同步数据。
+ 使用DiamondOps查看变更历史，一键回滚。
+ 使用DiamondOps查看推送轨迹。
+ 使用DiamondOps 定位 Diamond-client端问题。

1. 在ops中配置
```
# 简单值测试
stringValue=呵呵呵呵
intValue=123
booleanValue=true
chineseValue=哈哈哈

# 对象测试
user.name=a
user.age=24

# Spring 加载测试
# http服务器端口
server.port=8080
# endpoint配置
management.port=8081
```
2. 在 Spring 启动时加载
```java
@SpringBootApplication(scanBasePackages = {"yjt"})
@DiamondPropertySource(dataId = "yjt_diamond_test", groupId = "yjt")
public class Application {
    ...
}
```
3. 实时监听更改
监听类的实现
```java
@DiamondListener(dataId = "yjt_diamond_test", groupId = "yjt")
public class DiamondDataCallbackDemo implements DiamondDataCallback {

    private ConfigBean configBean = new ConfigBean();
    private RelaxedDataBinder binder = new RelaxedDataBinder(configBean);
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ConfigBean getConfigBean() {
        return configBean;
    }

    @Override
    public void received(String data) {
        try {
            Properties properties = new Properties();
            properties.load(new StringReader(data));
            System.err.println("received from diamond listener: " + properties);
            binder.bind(new MutablePropertyValues(properties));
            System.err.println(gson.toJson(configBean));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
config 对象的构造
```java
@Service
public class ConfigBean {
    private String stringValue;
    private int intValue;
    private boolean booleanValue;
    private User user;
    private String chineseValue;

    getter / setter

    public static class User {
        private String name;
        private int age;

        getter / setter
    }
}
```
诡异的是, 没有 getter / setter 就会注入失败


# 更多
[更多](http://gitlab.alibaba-inc.com/middleware-container/pandora-boot/wikis/spring-boot-diamond)