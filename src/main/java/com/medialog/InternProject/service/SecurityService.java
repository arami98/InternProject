package com.medialog.InternProject.service;


import com.medialog.InternProject.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


interface SecurityService {

    String createToken(String id);
}
