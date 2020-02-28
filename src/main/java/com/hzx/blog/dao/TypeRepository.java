package com.hzx.blog.dao;

import com.hzx.blog.model.Type;
import org.hibernate.validator.internal.engine.resolver.JPATraversableResolver;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/27
 */
public interface TypeRepository  extends JpaRepository<Type,Long>{

    Type findByName(String name);

}
