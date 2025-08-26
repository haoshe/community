package com.haoshe.community.service;

import com.haoshe.community.dao.UserMapper;
import com.haoshe.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// we need to find user based on it's id
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id){
        return userMapper.selectById(id);
    }
}
