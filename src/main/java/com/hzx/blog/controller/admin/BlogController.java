package com.hzx.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hzx.blog.model.*;
import com.hzx.blog.service.BlogService;
import com.hzx.blog.service.TagSerivce;
import com.hzx.blog.service.TypeSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/27
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private TypeSerivce typeSerivce;

    @Autowired
    private TagSerivce tagSerivce;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/blogs")
    public String blogs(Model model,
                        @PageableDefault(size = 3, sort = "updateTime", direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog) {

        List<Type> types = typeSerivce.getAll();

        Page<Blog> blogs = blogService.listBlog(pageable, blog);

        model.addAttribute("types", types);
        model.addAttribute("blogs", blogs);
        //查询所有标题
        return "admin/blogs";

    }

    @RequestMapping("/search")
    public  String search(Model model,
                  @PageableDefault(size = 3, sort = "updateTime", direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog) {


        Page<Blog> blogs = blogService.listBlog(pageable, blog);

        model.addAttribute("blogs",blogs);
        //查询所有标题
        return "admin/blogs :: blogList";

    }




    @RequestMapping("/blogs/input")
    public String input(Model model){

        List<Type> types = typeSerivce.getAll();

        List<Tag> tags = tagSerivce.getAll();
        model.addAttribute("types", types);
        model.addAttribute("tags", tags);

        return "admin/blogs-input";
    }

    @PostMapping("/blogs/save")
    public String save(Blog blog, HttpSession session){

        User user = (User) session.getAttribute("user");

        blog.setUser(user);

        blog.setType(typeSerivce.getTypeById(blog.getType().getId()));

        blog.setTags(tagSerivce.listTag(blog.getTagIds()));

        Blog saveblog = blogService.saveBlog(blog);

        return "redirect:/admin/blogs";
    }

    @RequestMapping("/blogs/delete")
    public String delete(Long id){

        blogService.deleBlogs(id);

        return "redirect:/admin/blogs";
    }


    @RequestMapping("/blogs/edit")
    public String toEdit(Model model,Long id){

        List<Type> types = typeSerivce.getAll();

        List<Tag> tags = tagSerivce.getAll();
        model.addAttribute("types", types);
        model.addAttribute("tags", tags);

        Blog blog = blogService.getBlogById(id);

        List<Tag> blogTags = blog.getTags();

        String tagIds = "";

        if(blogTags != null && blogTags.size() != 0){
            for (Tag blogTag:
                    blogTags) {
                tagIds += blogTag.getId() +",";
            }

            String tagIds2 = tagIds.substring(0,tagIds.lastIndexOf(","));

            blog.setTagIds(tagIds2);

        }
        model.addAttribute("blog",blog);

        return "admin/blogs-edit";
    }

    @PostMapping("/blogs/editBlog")
    public String edit(Blog blog, HttpSession session){


        blog.setType(typeSerivce.getTypeById(blog.getType().getId()));

        blog.setTags(tagSerivce.listTag(blog.getTagIds()));

        blog.setUpdateTime(new Date());

        Blog updateblog = blogService.updateBlog(blog);

        return "redirect:/admin/blogs";
    }

}
