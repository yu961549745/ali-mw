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
}
