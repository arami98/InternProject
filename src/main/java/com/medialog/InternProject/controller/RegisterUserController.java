package com.medialog.InternProject.controller;


import com.medialog.InternProject.model.User;
import com.medialog.InternProject.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class RegisterUserController {

    @Autowired
    RegisterUserService registerUserService;


    @PostMapping("/user/register")
    public String register(UserFrom from){
        try {
            User user=from.extractUser();
            registerUserService.register(user);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
