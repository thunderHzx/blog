package com.hzx.blog.service;

import com.hzx.blog.model.Blog;
import com.hzx.blog.model.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/27
 */
public interface BlogService {

    Page<Blog> listBlog(Pageable pageable,BlogQuery blog);

    Blog saveBlog(Blog blog);

    void deleBlogs(Long id);

    Blog getBlogById(Long id);

    Blog updateBlog(Blog blog);

    Page<Blog> listIndexBlogs(Pageable pageable);

    List<Blog> listRecommendBlog();

    Page<Blog> listSearchBlogs(String query,Pageable pageable);

    Blog findBlogMarkdown(Long id);

    Page<Blog> findBogsByTypeId(Long id, Pageable pageable);

    Page<Blog> findBogsByTagId(Long id, Pageable pageable);
}
