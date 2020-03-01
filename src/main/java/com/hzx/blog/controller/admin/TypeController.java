package com.hzx.blog.controller.admin;

import com.hzx.blog.model.Tag;
import com.hzx.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/27
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TagService tagService;

    @RequestMapping("/tags")
    public String types(@PageableDefault(size = 3,sort = "id",direction = Sort.Direction.DESC) Pageable pageable, Model model){

        Page<Tag> page = tagService.getPage(pageable);

        model.addAttribute("page",page);
        return "admin/tags";
    }

    @RequestMapping("/tags/input")
    public String input(){


        return "admin/tags-input";
    }

    @PostMapping("/saveTag")
    public String saveType(Tag type, RedirectAttributes redirectAttributes){

        Tag type2 = tagService.saveType(type);

        if(type2 == null){
            redirectAttributes.addFlashAttribute("message",type.getName()+"标签已经存在，请勿重复添加");
        }else {
            redirectAttributes.addFlashAttribute("message","添加了一条新的标签"+type.getName());
        }




        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/edit")
    public String edit(Tag type,Model model){

        model.addAttribute("type",type);

        return "admin/tags-edit";
    }

    @PostMapping("/editTag")
    public String editType(Tag type, RedirectAttributes redirectAttributes){

        Tag type2 = tagService.editType(type);

        redirectAttributes.addFlashAttribute("message","将"+type.getName()+"修改成了"+type2.getName());


        return "redirect:/admin/tags";
    }

    @RequestMapping("/deletTag")
    public String deletType(Tag type, RedirectAttributes redirectAttributes){

        tagService.deleteType(type);

        redirectAttributes.addFlashAttribute("message","删除了" + type.getName());


        return "redirect:/admin/tags";
    }

}
