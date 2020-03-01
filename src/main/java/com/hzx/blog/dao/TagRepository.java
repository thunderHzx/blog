package com.hzx.blog.dao;

import com.hzx.blog.model.Tag;
import com.hzx.blog.model.Type;
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
public interface TagRepository extends JpaRepository<Tag,Long> , JpaSpecificationExecutor {

    Tag findByName(String name);

    @Query("select t from Tag t")
    List<Tag> listTopTag(Pageable pageable);
}
