package com.medialog.InternProject.controller;


import com.medialog.InternProject.model.User;
import com.medialog.InternProject.repository.UserRepository;
import com.medialog.InternProject.service.RegisterUserService;
import com.medialog.InternProject.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class RegisterUserController {

    @Autowired
    RegisterUserService registerUserService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity register(UserFrom userFrom){


        try {
            User user = userFrom.extractUser();
            registerUserService.register(user);
            return ResponseEntity.ok().build();
        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
