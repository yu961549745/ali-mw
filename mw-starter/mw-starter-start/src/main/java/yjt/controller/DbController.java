package yjt.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
}
