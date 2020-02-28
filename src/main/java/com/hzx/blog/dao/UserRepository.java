package com.hzx.blog.dao;

import com.hzx.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 给我一瓶朗姆酒
 * 2020/2/26
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByUserNameAndPassword(String userName, String password);

}
