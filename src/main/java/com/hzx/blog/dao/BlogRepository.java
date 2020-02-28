package com.hzx.blog.dao;

import com.hzx.blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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






}
