package com.medialog.InternProject.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private Logger logger = LoggerFactory.getLogger(BearerAuthInterceptor.class);


    private final BearerAuthInterceptor bearerAuthInterceptor;

    public WebMvcConfig(BearerAuthInterceptor bearerAuthInterceptor) {
        this.bearerAuthInterceptor = bearerAuthInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){

       registry.addInterceptor(bearerAuthInterceptor)
               .addPathPatterns("/user/register")
               .addPathPatterns("/user/id-check/**")
               .addPathPatterns("/user/update/**")
               .addPathPatterns("/user/list");

        logger.info(">>> Register Interceptor");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/authorization")
                .allowedOrigins("*")
                .allowedMethods("GET","PUT","POST")
                .allowCredentials(false).maxAge(3600);
        registry.addMapping("/user")
                .allowedOrigins("*")
                .allowedMethods("GET","PUT","POST")
                .allowCredentials(false).maxAge(3600);
    }
}