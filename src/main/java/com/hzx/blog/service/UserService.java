package com.hzx.blog.service;

import com.hzx.blog.model.User;

/**
 * 给我一瓶朗姆酒
 * 2020/2/26
 */
public interface UserService {

    User checkLogin(String username, String password);
}
