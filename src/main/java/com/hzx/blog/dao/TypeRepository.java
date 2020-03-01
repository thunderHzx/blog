package com.hzx.blog.dao;

import com.hzx.blog.model.Type;
import org.hibernate.validator.internal.engine.resolver.JPATraversableResolver;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/27
 */
public interface TypeRepository  extends JpaRepository<Type,Long> , JpaSpecificationExecutor {

    Type findByName(String name);

    @Query("select t from Type t")
    List<Type> listTopType(Pageable pageable);
}
