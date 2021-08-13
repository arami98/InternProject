package com.medialog.InternProject.controller;


import com.medialog.InternProject.model.User;
import com.medialog.InternProject.network.RegisterResponse;
import com.medialog.InternProject.repository.UserRepository;
import com.medialog.InternProject.security.BearerAuthInterceptor;
import com.medialog.InternProject.service.RegisterUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/user")
public class RegisterUserController {

    private Logger logger = LoggerFactory.getLogger(BearerAuthInterceptor.class);

    @Autowired
    RegisterUserService registerUserService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(UserFrom userFrom){

        RegisterResponse registerResponse;
        try {
            User user = userFrom.extractUser();
            registerUserService.register(user);
            registerResponse=new RegisterResponse("REGISTER SUCCESS","SUCCESS");
            return ResponseEntity.ok().body(registerResponse);
        } catch (ParseException e) {
            logger.warn("ILLEGAL DATE FORM");
            registerResponse=new RegisterResponse(e.getMessage(),"Fail");
            return ResponseEntity.ok().body(registerResponse);
        } catch (IllegalArgumentException e){
            logger.warn("ILLEGAL register form");
            registerResponse=new RegisterResponse(e.getMessage(),"Fail");
            return ResponseEntity.ok().body(registerResponse);
        }
    }

}
