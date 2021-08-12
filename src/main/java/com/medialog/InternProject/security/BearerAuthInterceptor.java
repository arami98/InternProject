package com.medialog.InternProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BearerAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthorizationExtractor authExtractor;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public BearerAuthInterceptor(AuthorizationExtractor authExtractor, JwtTokenProvider jwtTokenProvider) {
        this.authExtractor = authExtractor;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        System.out.println(">>> interceptor.preHandle 호출");
        String token = authExtractor.extract(request, "Bearer");
        if (StringUtils.hasLength(token)) {
            return true;
        }

        if (!jwtTokenProvider.validateToken(token)) {
            throw new IllegalArgumentException("유효하지 않은 토큰");
        }

        String name = jwtTokenProvider.getSubject(token);
        request.setAttribute("name", name);
        return true;
    }
}