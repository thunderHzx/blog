package com.hzx.blog.dao;

import com.hzx.blog.model.Blog;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/27
 */
public interface BlogRepository extends JpaRepository<Blog,Long> , JpaSpecificationExecutor{

    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findAllByRecommend(Pageable pageable , boolean b);

    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findSearchBlogs(String s, Pageable pageable);

    @Modifying
    @Query("update Blog  set views = views + 1 where id = ?1")
    void updateViews(Long id);
}
