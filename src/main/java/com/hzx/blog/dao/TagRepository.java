package com.hzx.blog.dao;

import com.hzx.blog.model.Tag;
import com.hzx.blog.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/27
 */
public interface TagRepository extends JpaRepository<Tag,Long>{

    Tag findByName(String name);
}
