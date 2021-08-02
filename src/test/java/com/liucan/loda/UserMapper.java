package com.liucan.loda;

import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liucan
 * @date 2021/8/2
 */
public interface UserMapper {
    //根据用户id查询用户信息
    User findUserById(Integer id) throws Exception;

    User findUserByUsername(String name) throws Exception;

    @Select("select user_id as userId, user_name as userName from user")
    List<User> selectList();
}
