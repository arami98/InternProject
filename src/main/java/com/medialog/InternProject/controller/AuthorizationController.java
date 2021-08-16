package com.medialog.InternProject.controller;


import com.medialog.InternProject.security.TokenResponse;
import com.medialog.InternProject.service.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorization")
public class AuthorizationController {
    
    @Autowired
    SecurityServiceImpl securityService;

    @GetMapping("/token")
    public ResponseEntity<TokenResponse> token(){
        String token = securityService.createToken("medialog");
        TokenResponse tokenResponse = new TokenResponse(token, "Bearer");
        return new ResponseEntity<TokenResponse>(tokenResponse, HttpStatus.OK);
    }

}
