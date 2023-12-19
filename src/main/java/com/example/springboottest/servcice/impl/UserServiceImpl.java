package com.example.springboottest.servcice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboottest.domain.User;
import com.example.springboottest.mapper.UserMapper;
import com.example.springboottest.servcice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return userMapper.selectOne(wrapper);
    }
}
