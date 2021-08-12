package com.medialog.InternProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private HandlerInterceptor bearerAuthInterceptor;


    public void addInterceptors(InterceptorRegistry registry){
        System.out.println(">>> Register Interceptor");
        registry.addInterceptor(bearerAuthInterceptor).addPathPatterns("/info");
    }
}