package com.medialog.InternProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.medialog.InternProject.repository.UserRepository;

import lombok.Getter;
import lombok.Setter;

@RestController
public class idCheckController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/user/id-check/{id}")
	public IdCheckResponse idCheck(@PathVariable String id) {
		IdCheckResponse idCheckRes = new IdCheckResponse();
		boolean flag = userRepository.existsById(id);
		String message, status;
		
		if(flag == true) {
			message = "이미 사용중인 id입니다.";
			status = "UNUSABLE";
		}
		else {
			message = "사용 가능한 id입니다.";
			status = "USABLE";
		}
		
		idCheckRes.setMessage(message);
		idCheckRes.setResult(status);
		return idCheckRes;
	}
	
	@Getter
	@Setter
	static class IdCheckResponse{
		private String result;
		private String message;
	}
}
