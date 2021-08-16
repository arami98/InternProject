package com.medialog.InternProject.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medialog.InternProject.model.User;
import com.medialog.InternProject.repository.UserRepository;

@RestController
public class UpdateUserController {

	@Autowired
	private UserRepository userRepository;

	@PutMapping("/user/update/{id}")
	public User updateUser(@PathVariable String id, @RequestBody String addr) throws ParseException {
		User userUpdate = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 사용자가 없습니다.");
		});
		
		userUpdate.setAddr(addr);
		userUpdate.setUpdateDt(new Date());
		return userRepository.save(userUpdate);
	}
}
