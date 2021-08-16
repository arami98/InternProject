package com.medialog.InternProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medialog.InternProject.model.User;
import com.medialog.InternProject.repository.UserRepository;

@RestController
public class UserListController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/user/list")
	public List<User> selectAll() {
		List<User> list = userRepository.findAll();

		return list;
	}

}
