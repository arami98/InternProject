package com.medialog.InternProject.controller;


import com.medialog.InternProject.model.User;
import com.medialog.InternProject.network.RegisterResponse;
import com.medialog.InternProject.network.UserFromVO;
import com.medialog.InternProject.repository.UserRepository;
import com.medialog.InternProject.security.BearerAuthInterceptor;
import com.medialog.InternProject.service.RegisterUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<RegisterResponse> register(@RequestBody UserFromVO userFrom){
        if(userFrom == null){
            throw new NullPointerException();
        }

        try {
            return successResponse(userFrom);
        } catch (ParseException e) {
            return dateFromException(e);
        } catch (IllegalArgumentException e){
            return registerFormException(e);
        }
    }

    private ResponseEntity<RegisterResponse> registerFormException(IllegalArgumentException e) {
        RegisterResponse registerResponse;
        logger.warn("ILLEGAL REGISTER FORM");
        registerResponse=new RegisterResponse(e.getMessage(),"Fail");
        return new ResponseEntity(registerResponse, HttpStatus.OK);
    }

    private ResponseEntity<RegisterResponse> dateFromException(ParseException e) {
        RegisterResponse registerResponse;
        logger.warn("ILLEGAL DATE FORM");
        registerResponse=new RegisterResponse(e.getMessage(),"Fail");
        return new ResponseEntity(registerResponse, HttpStatus.OK);
    }

    private ResponseEntity successResponse(UserFromVO userFrom) throws ParseException {
        RegisterResponse registerResponse;
        User user = userFrom.extractUser();
        registerUserService.register(user);
        registerResponse=new RegisterResponse("REGISTER SUCCESS","SUCCESS");
        return new ResponseEntity(registerResponse, HttpStatus.OK);
    }

}
