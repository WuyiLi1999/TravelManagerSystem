package com.example.springboottest.servcice;

import com.example.springboottest.domain.User;

/**
 * @author lwy
 *
 */
public interface UserService {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User queryUserByUsername(String username);
}
