package com.medialog.InternProject.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private Logger logger = LoggerFactory.getLogger(BearerAuthInterceptor.class);

    @Autowired
    private HandlerInterceptor bearerAuthInterceptor;


    public void addInterceptors(InterceptorRegistry registry){
        logger.info(">>> Register Interceptor");
        registry.addInterceptor(bearerAuthInterceptor).addPathPatterns("/api/test/user");
    }
}