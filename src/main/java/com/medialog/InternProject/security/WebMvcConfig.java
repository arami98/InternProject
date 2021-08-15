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
               .addPathPatterns("/")
               .excludePathPatterns("/authorization");

        logger.info(">>> Register Interceptor");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/authorization/token")
                .allowedOrigins("*")
                .allowedMethods("GET")
                .allowCredentials(false).maxAge(3600);

        registry.addMapping("/user/list")
                .allowedOrigins("*")
                .allowedMethods("GET")
                .allowCredentials(false).maxAge(3600);

        registry.addMapping("/user/id-check/**")
                .allowedOrigins("*")
                .allowedMethods("GET")
                .allowCredentials(false).maxAge(3600);

        registry.addMapping("/user/update/**")
                .allowedOrigins("*")
                .allowedMethods("PUT")
                .allowCredentials(false).maxAge(3600);

        registry.addMapping("/user/register")
                .allowedOrigins("*")
                .allowedMethods("POST")
                .allowCredentials(false).maxAge(3600);


    }
}