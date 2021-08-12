package com.medialog.InternProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medialog.InternProject.model.User;
import com.medialog.InternProject.repository.UserRepository;

@RestController
public class putController {

	@Autowired
	private UserRepository userRepository;

	@PutMapping("/test/user/{id}")
	public User selectOne(@PathVariable String id, @RequestBody User user) {
		user.setLoginId(id);
		
		return null;
	}
}
