package com.medialog.InternProject.service;

import com.medialog.InternProject.model.User;
import com.medialog.InternProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public String register(User user) {
        System.out.printf(user.getName());
        userRepository.save(user);
        return user.getName();
    }
}
