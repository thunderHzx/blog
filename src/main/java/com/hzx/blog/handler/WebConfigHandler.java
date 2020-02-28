package com.hzx.blog.handler;

import com.hzx.blog.controller.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/27
 */
@Configuration
public class WebConfigHandler implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin","/admin/login");
    }
}
