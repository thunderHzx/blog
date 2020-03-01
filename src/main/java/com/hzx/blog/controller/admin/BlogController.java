package com.hzx.blog.controller.admin;

import com.hzx.blog.model.*;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/27
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/blogs")
    public String blogs(Model model,
                        @PageableDefault(size = 3, sort = "updateTime", direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog) {

        List<Type> types = typeService.getAll();

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

        List<Type> types = typeService.getAll();

        List<Tag> tags = tagService.getAll();
        model.addAttribute("types", types);
        model.addAttribute("tags", tags);

        return "admin/blogs-input";
    }

    @PostMapping("/blogs/save")
    public String save(Blog blog, HttpSession session){

        User user = (User) session.getAttribute("user");

        blog.setUser(user);

        blog.setType(typeService.getTypeById(blog.getType().getId()));

        blog.setTags(tagService.listTag(blog.getTagIds()));

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

        List<Type> types = typeService.getAll();

        List<Tag> tags = tagService.getAll();
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


        blog.setType(typeService.getTypeById(blog.getType().getId()));

        blog.setTags(tagService.listTag(blog.getTagIds()));

        blog.setUpdateTime(new Date());

        Blog updateblog = blogService.updateBlog(blog);

        return "redirect:/admin/blogs";
    }

}
