package com.hzx.blog.controller;

import com.hzx.blog.model.Blog;
import com.hzx.blog.model.Tag;
import com.hzx.blog.model.Type;
import com.hzx.blog.service.BlogService;
import com.hzx.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/29
 */
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @RequestMapping("/index/tags/{id}")
    private String tags(@PathVariable Long id, Model model,
                        @PageableDefault(size = 3, sort = "updateTime", direction = Sort.Direction.DESC) Pageable pageable){


        //Page<Tag> page = tagService.findTagsById(id,pageable);


        List<Tag> tags = tagService.listTopTag();

        if(id == 0){
            id = tags.get(0).getId();
        }

        Page<Blog> page = blogService.findBogsByTagId(id,pageable);

        model.addAttribute("page",page);
        model.addAttribute("tags",tags);
        model.addAttribute("activeTagId",id);

        return "tags";
    }

}
