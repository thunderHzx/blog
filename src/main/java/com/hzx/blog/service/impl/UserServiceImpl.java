package com.hzx.blog.service.impl;

import com.hzx.blog.dao.UserRepository;
import com.hzx.blog.model.User;
import com.hzx.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkLogin(String username, String password) {

        User user = userRepository.findUserByUserNameAndPassword(username,password);

        return user;
    }
}
