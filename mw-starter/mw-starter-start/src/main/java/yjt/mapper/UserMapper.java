package yjt.mapper;

import org.apache.ibatis.annotations.Mapper;
import yjt.data.User;

import java.util.List;

/**
 * @author 鱼泡
 */
@Mapper
public interface UserMapper {
    List<User> getAllUser();

    List<User> getUserByName(String name);

    void insertByUser(User user);

    void insertUsers(List<User> users);

    void updateUser(User user);

    void deleteByName(String name);
}
