package com.haoshe.community.dao;

import com.haoshe.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    // return the number of rows inserted
    int insertUser(User user);
    // return the number of rows affected by the operation
    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);

}