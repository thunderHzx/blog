package com.hzx.blog.controller;

import com.hzx.blog.model.Blog;
import com.hzx.blog.model.BlogQuery;
import com.hzx.blog.model.Tag;
import com.hzx.blog.model.Type;
import com.hzx.blog.service.BlogService;
import com.hzx.blog.service.TagService;
import com.hzx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/26
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @RequestMapping("/index")
    public String index(Model model,
                        @PageableDefault(size = 8, sort = "updateTime", direction = Sort.Direction.DESC) Pageable pageable){
        //分页展示博客详情页面
        Page<Blog> page = blogService.listIndexBlogs(pageable);
        //展示排行前六名的type
        List<Type> types = typeService.listTopType();
        //展示排行前六名的tag
        List<Tag> tags = tagService.listTopTag();
        //获取更新前六名的推荐
        List<Blog> recommendBlog = blogService.listRecommendBlog();

        model.addAttribute("page",page);
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        model.addAttribute("recommendBlogs",recommendBlog);
        return "index";
    }

    @RequestMapping("/index/search")
    public String search(String query,
                         Model model,
                         @PageableDefault(size = 8, sort = "updateTime", direction = Sort.Direction.DESC) Pageable pageable){

        //分页展示博客详情页面
        Page<Blog> page = blogService.listSearchBlogs(query,pageable);

        model.addAttribute("page",page);

        return "search";
    }

    @RequestMapping("/index/blog")
    public String blog(Long id,
                         Model model){

        //分页展示博客详情页面
        Blog blog = blogService.findBlogMarkdown(id);

        model.addAttribute("blog",blog);

        return "blog";
    }

    @RequestMapping("/index/about")
    public String about(){


        return "about";
    }

}
