package com.hzx.blog.controller;

import com.hzx.blog.model.Blog;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/29
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/index/types/{id}")
    private String tags(@PathVariable Long id, Model model,
                        @PageableDefault(size = 3, sort = "updateTime", direction = Sort.Direction.DESC) Pageable pageable){





        List<Type> types = typeService.listTopType();

        if (id == 0) {
            id = types.get(0).getId();
        }

        Page<Blog> page = blogService.findBogsByTypeId(id,pageable);

        model.addAttribute("page",page);
        model.addAttribute("types",types);
        model.addAttribute("activeTypeId",id);

        return "types";
    }

}
