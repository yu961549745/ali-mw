package yjt.controller;

import org.springframework.web.bind.annotation.*;
import yjt.data.User;

/**
 * 演示返回 Response Body
 *
 * @author 鱼泡
 */
@RestController
@RequestMapping("/resp")
public class RespController {
    /**
     * 简单参数绑定
     *
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("test01")
    public String test01(String name, int age) {
        return String.format("name: %s, age: %d", name, age);
    }

    /**
     * 可选参数绑定
     *
     * @param name
     * @return
     */
    @RequestMapping("test02")
    public String test02(@RequestParam(name = "name", required = false, defaultValue = "visitor") String name) {
        return "hello " + name;
    }

    /**
     * 对象作为参数
     *
     * @param user
     * @return
     */
    @RequestMapping("test03")
    public String test03(@RequestBody User user) {
        return String.format("name: %s, age: %d", user.getName(), user.getAge());
    }

    /**
     * 对象作为返回值
     *
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("test04")
    public User test04(String name, int age) {
        User user = new User();
        user.setName(name + "\n");
        user.setAge(age);
        return user;
    }

    /**
     * 测试路径名称
     *
     * @param name
     * @return
     */
    @RequestMapping("/path/{name}")
    public String test05(@PathVariable String name) {
        return name;
    }

}
