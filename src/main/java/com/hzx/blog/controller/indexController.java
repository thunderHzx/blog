package com.hzx.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/26
 */
@Controller
public class indexController {

    @RequestMapping("/index")
    public String index(){
        //int i = 10/0;
        return "index";
    }
}
