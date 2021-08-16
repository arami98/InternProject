package com.medialog.InternProject.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private Logger logger = LoggerFactory.getLogger(BearerAuthInterceptor.class);

    private final String BASEURL = "http://localhost:3000";


    private final BearerAuthInterceptor bearerAuthInterceptor;

    public WebMvcConfig(BearerAuthInterceptor bearerAuthInterceptor) {
        this.bearerAuthInterceptor = bearerAuthInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry){

        registry.addInterceptor(bearerAuthInterceptor)
                .addPathPatterns("/user/**");
        logger.info(">>> Register Interceptor");
    }


}