package yjt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yjt.data.User;
import yjt.mapper.UserMapper;

import java.util.List;

/**
 * @author 鱼泡
 */
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
