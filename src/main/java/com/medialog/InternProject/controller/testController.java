package com.medialog.InternProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.medialog.InternProject.model.User;
import com.medialog.InternProject.repository.UserRepository;

@RestController
public class testController {

	@Autowired
	private UserRepository userRepository;

	// context-path : /api
	@GetMapping("/test/user/{id}")
	public User selectOne(@PathVariable String id) {
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 사용자가 없습니다.");
		});

		return user;
	}
	
	@GetMapping("/test/user")
	public List<User> selectAll() {
		List<User> list = userRepository.findAll();

		return list;
	}
}
