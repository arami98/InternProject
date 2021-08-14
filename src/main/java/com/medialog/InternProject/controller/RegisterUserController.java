package com.medialog.InternProject.controller;


import com.medialog.InternProject.model.User;
import com.medialog.InternProject.network.RegisterResponse;
import com.medialog.InternProject.network.UserFrom;
import com.medialog.InternProject.repository.UserRepository;
import com.medialog.InternProject.security.BearerAuthInterceptor;
import com.medialog.InternProject.service.RegisterUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

        HttpHeaders headers = noCorsHttpHeader();

        try {
            return sucessResponse(userFrom, headers);
        } catch (ParseException e) {
            return dateFromException(headers, e);
        } catch (IllegalArgumentException e){
            return registerFormException(headers, e);
        }
    }

    private ResponseEntity<RegisterResponse> registerFormException(HttpHeaders headers, IllegalArgumentException e) {
        RegisterResponse registerResponse;
        logger.warn("ILLEGAL register form");
        registerResponse=new RegisterResponse(e.getMessage(),"Fail");
        return new ResponseEntity(registerResponse,headers, HttpStatus.OK);
    }

    private ResponseEntity<RegisterResponse> dateFromException(HttpHeaders headers, ParseException e) {
        RegisterResponse registerResponse;
        logger.warn("ILLEGAL DATE FORM");
        registerResponse=new RegisterResponse(e.getMessage(),"Fail");
        return new ResponseEntity(registerResponse,headers, HttpStatus.OK);
    }

    private ResponseEntity sucessResponse(UserFrom userFrom, HttpHeaders headers) throws ParseException {
        RegisterResponse registerResponse;
        User user = userFrom.extractUser();
        registerUserService.register(user);
        registerResponse=new RegisterResponse("REGISTER SUCCESS","SUCCESS");
        return new ResponseEntity(registerResponse, headers, HttpStatus.OK);
    }

    private HttpHeaders noCorsHttpHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("mode","no-cors");
        return headers;
    }

}
