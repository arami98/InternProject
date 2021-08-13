package com.medialog.InternProject.controller;


import com.medialog.InternProject.security.TokenResponse;
import com.medialog.InternProject.service.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        //todo cors에러 해결
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("mode","no-cors");
        String token = securityService.createToken("medialog");
        TokenResponse tokenResponse = new TokenResponse(token, "bearer");
        ResponseEntity<TokenResponse> responseResponseEntity= new ResponseEntity<TokenResponse>(tokenResponse,headers, HttpStatus.BAD_REQUEST);
        return   responseResponseEntity;
    }
}
