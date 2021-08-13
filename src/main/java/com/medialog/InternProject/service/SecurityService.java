package com.medialog.InternProject.service;


import com.medialog.InternProject.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    TokenProvider jwtTokenProvider;

    public String createToken(String id) {
        return jwtTokenProvider.createToken(id);
    }
}
