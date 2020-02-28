package com.hzx.blog.controller.admin;

import com.hzx.blog.model.User;
import com.hzx.blog.service.UserService;
import com.hzx.blog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 给我一瓶朗姆酒
 * 2020/2/26
 */
@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String toLoginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "username",required = true) String username,
                        @RequestParam(name = "password",required = true) String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes){

        User user = userService.checkLogin(username, MD5Util.md5(password));

        if(user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            redirectAttributes.addFlashAttribute("message","用户名或者密码错误");

            return "redirect:/admin";
        }

    }

    @GetMapping("logout")
    public String logout(HttpSession session){

       session.removeAttribute("user");

       return "redirect:/admin";
    }
}
