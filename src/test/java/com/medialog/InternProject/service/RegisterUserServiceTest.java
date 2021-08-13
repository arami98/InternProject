package com.medialog.InternProject.service;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegisterUserServiceTest {

    RegisterUserService registerUserService;

    @BeforeEach
    void setUp() {
        registerUserService=new RegisterUserServiceImpl();
    }
}