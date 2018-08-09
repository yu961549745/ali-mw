# ali-mw
阿里巴巴中间件使用测试

学习计划
+ Pandora Boot / Spring Boot
+ Spring MVC
+ Velocity
+ MyBatis
+ HSF
+ TDDL
+ ONS

# 快速入门

+ 本页
    + Pandora Boot + Spring Boot
    + Spring MVC + Velocity
    + MyBatis + MySQL
+ [HSF](docs/HSF.md)
+ [TDDL](docs/TDDL.md)
+ [ONS](docs/ONS.md)

# Pandora Boot + Spring Boot 
首先，Pandora Boot 与 Spring Boot 无缝集成，使用 Pandora Boot 的同时也可以充分享受 Spring Boot 的便利；

其次，Pandora Boot 是在 Pandora 的基础之上，发展出的更轻量使用集团中间件的方式；它基于 Pandora 和 Fat Jar 技术，可以直接在 IDE 里启动 Pandora 环境，开发调试等效率都大大提高；

```java
// Spring Boot App 标记
@SpringBootApplication(scanBasePackages = {"yjt"})
public class Application {

    public static void main(String[] args) {
        // 启动 Pandora Boot
        PandoraBootstrap.run(args);
        // 启动 Spring Boot
        SpringApplication.run(Application.class, args);
        // 标记 Pandora Boot 启动完成
        PandoraBootstrap.markStartupAndWait();
    }
}
```

Spring Boot 的配置文件为 `application.properties`, 例如服务器端口配置
```
# http服务器端口
server.port=7001
```

Maven 依赖
```xml
<!-- Spring Boot  -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
<!-- Spring MVC -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<!-- Spring Boot 监控 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<!-- Volecity 模板引擎 -->
<dependency>
    <groupId>com.alibaba.boot</groupId>
    <artifactId>velocity-spring-boot-starter</artifactId>
</dependency>
<!-- Spring 测试 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-test</artifactId>
    <scope>test</scope>
</dependency>
```

# Spring MVC + Velocity
## 返回 View 的 Controller
```java
@Controller
public class PageController {
    @RequestMapping("/hello")
    public String hello(String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}
```
+ `@Controller` 声明一个 MVC 的 Controller
+ `@RequestMapping` 指定指定方法的访问路径 `webroot/hello`
+ 对于简单方法, 参数能够直接绑定, 也就是接收 `name` 参数
+ `Model` 作为附加参数, 可以设置 View 中的值
+ 最终返回 `"hello"` 是 View 的名称

Spring MVC 支持多种模板引擎
+ JSP
+ Thymeleaf
+ FreeMarker
+ Velocity 是目前阿里使用的引擎, 但是已经在 Spring 4.3 Deprecated. 
+ Groovy
+ JMustache 
+ Pebble 
+ Jade4j

下面以 Velocity 为例进行说明

默认生成的 Velocity 配置如下
```
# 指定 Velocity View 根目录
spring.velocity.resource-loader-path=classpath:/velocity/templates
# 指定默认 Layout
spring.velocity.layout-url=/velocity/layout/default.vm
# 指定自带 Toolbox 配置, 可以理解为配置工具函数
spring.velocity.toolbox-config-location=/velocity/toolbox.xml
# 指定自定义 Toolbox 搜索目录
spring.velocity.tools-base-packages=yjt.velocity
```

可以认为
```
页面 = Layout + Template + Model
```
+ Layout 指定网页的公共部分, 例如 html 头尾, 导航栏, 页脚等等
+ Template 指定需要渲染的模板
+ Model 指定了 Template 中的相关数据

编写 Template
```html
<h1>Hello $!{name}</h1>
```
+ `$name` 在为空时原样打印
+ `$!name` 在为空时打印空字符串
+ 加入`{}`不影响效果, 但在分隔连续字符时使用, 例如`${name}xxx`

编写 Layout
```html
<!DOCTYPE html>
<html>

<head>
    <title>mw-test</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
</head>

<body>
    $!{screen_content}
    <hr/>
    <a href="/">Home</a>
</body>

</html>
```
其中 `screen_content` 就是 template 渲染的结果

## Velocity 进阶
自定义 Tool
```java
@DefaultKey("dateTool")// 指定调用的 key
public class DateTool extends SafeConfig {
    public Date now() {
        return new Date();
    }
}
```
指定 Layout
```java
@GetMapping("/")
@VelocityLayout("/velocity/layout/index.vm")
public String root() {
    return "index";
}
```
对应文件
```html
<!DOCTYPE html>
<html>

<head>
    <title>mw-test</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
</head>

<body>
<h1>It works.</h1>
<br/>
<pre>
    Locale :  $!{text.getLocale()}
    Date   :  $!{dateTool.now()}
    </pre>
<hr/> $!{screen_content}
<hr/>
</body>

</html>
```
## 返回 Response Body 的 Controller
```java
@RestController
@RequestMapping("/resp")
public class RespController {
    ...
}
```
+ `@RestController` 指定了一个返回 response body 的  controller
+ `@RequestMapping` 在类上指定, 相当于指定了一个父路径, 内部方法再声明则继承父路径

简单参数能够自动绑定
```java
@RequestMapping("test01")
public String test01(String name, int age) {
    return String.format("name: %s, age: %d", name, age);
}
```

还能指定默认参数
```java
@RequestMapping("test02")
public String test02(@RequestParam(name = "name", required = false, defaultValue = "visitor") String name) {
    return "hello " + name;
}
```

也可以自动解析 JSON 对象作为参数
```java
@RequestMapping("test03")
public String test03(@RequestBody User user) {
    return String.format("name: %s, age: %d", user.getName(), user.getAge());
}
```
对应的请求为
```javascript
$.ajax({
    method: 'post',
    url: 'http://localhost:7001/resp/test03',
    contentType: 'application/json',
    data: JSON.stringify({ name: 'haha', age: 1 }),
    success: (data) => { console.log(data) }
})
```
需要注意的点就是
+ 指定 `contentType: 'application/json'`
+ JS 对象需要 `JSON.stringify`

返回对象, 直接返回, 浏览器会获得 Json 字符串
```java
@RequestMapping("test04")
public User test04(String name, int age) {
    User user = new User();
    user.setName(name + "\n");
    user.setAge(age);
    return user;
}
```

用路径名代替参数值
```java
@RequestMapping("/path/{name}")
public String test05(@PathVariable String name) {
    return name;
}
```
+ `@RequestMapping("/path/{name}")` 中 `{name}` 绑定到变量
+ `@PathVariable` 进行路径变量的声明

### 既返回 view 又返回 response body 的 controller
```java
@Controller
@RequestMapping("/mix")
public class MixController {
    @RequestMapping("/page")
    public String page() {
        return "hello";
    }

    @RequestMapping("/resp")
    @ResponseBody
    public String resp() {
        return "hello world";
    }
}
```
`@Controller`+`@ResponseBody`=`@RestController`

# MyBatis + MySQL
1. 配置数据源
`application.properties` 
```
# 指定配置文件位置
mybatis.config-location=classpath:/mybatis/mybatis-config.xml
# mysql 数据源配置
spring.datasource.url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=true&
spring.datasource.username=root
spring.datasource.password=12345678
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```
Maven
```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.46</version>
</dependency>
```
2. MyBitas 配置文件
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <mappers>
        <mapper resource="mybatis/salary-mapper.xml"/>
    </mappers>
</configuration>
```
3. 配置 Mapper
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="yjt.mapper.UserMapper">

    <select id="getAllUser" resultType="yjt.data.User">
        select * from `user`;
    </select>

    <select id="getUserByName" resultType="yjt.data.User">
        select * from `user` where `name`=#{name};
    </select>

    <insert id="insertByUser" parameterType="yjt.data.User">
        insert into `user` (`name`,age) values
        (#{name},#{age});
    </insert>

    <insert id="insertUsers" parameterType="java.util.List">
        insert into `user`(`name`,age) values
        <foreach collection="list" item="user" separator=",">
            (#{user.name},#{user.age})
        </foreach>
    </insert>

    <update id="updateUser" parameterType="yjt.data.User">
        update `user` set
        `name`=#{name},
        age=#{age}
        where `name`=#{name};
    </update>

    <delete id="deleteByName">
        delete from `user` where `name`=#{name};
    </delete>
    
</mapper>
```
4. 编写 Java 接口
```java
@Mapper
public interface UserMapper {
    List<User> getAllUser();

    List<User> getUserByName(String name);

    void insertByUser(User user);

    void insertUsers(List<User> users);

    void updateUser(User user);

    void deleteByName(String name);
}
```
5. 调用 Java 接口
```java
@RestController
@RequestMapping("/db")
public class DbController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/get-all-user")
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @RequestMapping("/get-user-by-name")
    public List<User> getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @RequestMapping("/insert-by-user")
    public List<User> insertByUser(String name, int age) {
        userMapper.insertByUser(newUser(name, age));
        return userMapper.getAllUser();
    }

    @RequestMapping("/insert-users")
    public List<User> insertUsers(@RequestBody List<User> users) {
        userMapper.insertUsers(users);
        return userMapper.getAllUser();
    }

    @RequestMapping("/update-user")
    public User updateUser(String name, int age) {
        User user = newUser(name, age);
        userMapper.updateUser(user);
        return user;
    }

    @RequestMapping("/delete-by-name")
    public List<User> deleteByName(String name) {
        userMapper.deleteByName(name);
        return userMapper.getAllUser();
    }

    private User newUser(String name, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }
}
```
6. 外部调用测试
```javascript
url = 'http://localhost:7001/db'
function success(data) { console.log(data) }
$.get(url + '/get-all-user', '', success)
$.get(url + '/get-user-by-name', 'name=b', success)
$.get(url + '/insert-by-user', 'name=c&age=123', success)
$.ajax({
    method: 'post',
    url: url + '/insert-users',
    contentType: 'application/json',
    data: JSON.stringify([{ name: 'haha', age: 1 }, { name: 'hehe', age: 2 }]),
    success: (data) => { console.log(data) }
})
$.get(url + '/update-user', 'name=b&age=123', success)
$.get(url + '/delete-by-name', 'name=haha', success)
```
