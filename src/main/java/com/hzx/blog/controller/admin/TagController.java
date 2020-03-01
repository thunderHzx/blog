package com.hzx.blog.controller.admin;

import com.hzx.blog.model.Type;
import com.hzx.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/27
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("/types")
    public String types(@PageableDefault(size = 3,sort = "id",direction = Sort.Direction.DESC) Pageable pageable, Model model){

        Page<Type> page = typeService.getPage(pageable);

        model.addAttribute("page",page);
        return "admin/types";
    }

    @RequestMapping("/types/input")
    public String input(){


        return "admin/types-input";
    }

    @PostMapping("/saveType")
    public String saveType(Type type, RedirectAttributes redirectAttributes){

        Type type2 = typeService.saveType(type);

        if(type2 == null){
            redirectAttributes.addFlashAttribute("message",type.getName()+"标签已经存在，请勿重复添加");
        }else {
            redirectAttributes.addFlashAttribute("message","添加了一条新的标签"+type.getName());
        }




        return "redirect:/admin/types";
    }

    @GetMapping("/types/edit")
    public String edit(Type type,Model model){

        model.addAttribute("type",type);

        return "admin/types-edit";
    }

    @PostMapping("/editType")
    public String editType(Type type, RedirectAttributes redirectAttributes){

        Type type2 = typeService.editType(type);

        redirectAttributes.addFlashAttribute("message","将"+type.getName()+"修改成了"+type2.getName());


        return "redirect:/admin/types";
    }

    @RequestMapping("/deletType")
    public String deletType(Type type, RedirectAttributes redirectAttributes){

        typeService.deleteType(type);

        redirectAttributes.addFlashAttribute("message","删除了" + type.getName());


        return "redirect:/admin/types";
    }

}
