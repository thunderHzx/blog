package com.hzx.blog.service.impl;

import com.hzx.blog.dao.BlogRepository;
import com.hzx.blog.model.Blog;
import com.hzx.blog.model.BlogQuery;
import com.hzx.blog.model.Tag;
import com.hzx.blog.model.Type;
import com.hzx.blog.service.BlogService;
import com.hzx.blog.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.expression.Lists;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/27
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Page<Blog> listBlog(Pageable pageable ,BlogQuery blog) {

        Specification querySpe = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();
                if(!"".equals(blog.getTitle()) && blog.getTitle() != null){
                    predicates.add(cb.like(root.<String>get("title"),
                            "%" + blog.getTitle() + "%"));
                }

                if(blog.getTypeId() != null){
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }

                if(blog.isRecommend()){
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }

                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        };

        return blogRepository.findAll(querySpe,pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {

        blog.setUpdateTime(new Date());

        blog.setCreateTime(new Date());

        blog.setViews(0);

        Blog saveBlog = blogRepository.save(blog);

        return saveBlog;
    }

    @Override
    public void deleBlogs(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Blog getBlogById(Long id) {
        Blog blog = blogRepository.getOne(id);
        return blog;
    }

    @Override
    public Blog updateBlog(Blog blog) {
        return blogRepository.saveAndFlush(blog);
    }

    @Override
    public Page<Blog> listIndexBlogs(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    /**
     * 获取最新推荐的六个博客
     * @return
     */
    @Override
    public List<Blog> listRecommendBlog() {

        Sort sort = Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable = PageRequest.of(0,6,sort);

        return blogRepository.findAllByRecommend(pageable,true);
    }

    @Override
    public Page<Blog> listSearchBlogs(String query, Pageable pageable) {
        return blogRepository.findSearchBlogs("%"+query+"%",pageable);
    }

    @Transactional
    @Override
    public Blog findBlogMarkdown(Long id) {

        Blog querBlog = blogRepository.getOne(id);

        Blog b = new Blog();
        BeanUtils.copyProperties(querBlog,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        blogRepository.updateViews(id);
        return b;
    }

    @Override
    public Page<Blog> findBogsByTypeId(Long id, Pageable pageable) {

        Specification querySpe = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();
                if(id != null && id != 0 ){
                    predicates.add(cb.equal(root.<Tag>get("type").get("id"),
                            id));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));

                return null;
            }
        };

        return blogRepository.findAll(querySpe,pageable);


    }

    @Override
    public Page<Blog> findBogsByTagId(Long id, Pageable pageable) {

        Specification querySpe = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {

                Join join = root.join("tags");
                return cb.equal(join.get("id"),id);
            }
        };

        return blogRepository.findAll(querySpe,pageable);


    }

}
