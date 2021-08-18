package com.medialog.InternProject.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BearerAuthInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(BearerAuthInterceptor.class);

    private AuthorizationExtractor authExtractor;
    private JwtTokenProvider jwtTokenProvider;

    public BearerAuthInterceptor(AuthorizationExtractor authExtractor, JwtTokenProvider jwtTokenProvider) {
        this.authExtractor = authExtractor;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        logger.info(">>> interceptor.preHandle Called");
        String token = authExtractor.extract(request, "Bearer ");

        if (checkIfEmpty(token)) return false;

        checkIfValidToken(token);

        String name = jwtTokenProvider.getSubject(token);

        request.setAttribute("name", name);
        return true;
    }

    private boolean checkIfEmpty(String token) {
        return !StringUtils.hasLength(token);
    }

    private void checkIfValidToken(String token) {
        if (!jwtTokenProvider.validateToken(token)) {
            throw new IllegalArgumentException("INVALID TOKEN");
        }
    }
}