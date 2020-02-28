package com.hzx.blog.controller.interceptor;


import com.hzx.blog.model.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/27
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            response.sendRedirect("/admin");
            return false;
        }


        return true;
    }
}
