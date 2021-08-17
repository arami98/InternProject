package com.medialog.InternProject.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medialog.InternProject.model.User;
import com.medialog.InternProject.network.RegisterResponse;
import com.medialog.InternProject.repository.UserRepository;

@RestController
public class UpdateUserController {

	@Autowired
	private UserRepository userRepository;

	@PutMapping("/user/update/{id}")
	public ResponseEntity<RegisterResponse> updateUser(@PathVariable String id, @RequestBody String addr)
			throws ParseException {
		try {
			return successResponse(id, addr);
		} catch (IllegalArgumentException e) {
			return notUser(e);
		}

	}

	private ResponseEntity<RegisterResponse> notUser(IllegalArgumentException e) {
		RegisterResponse registerResponse;
		registerResponse = new RegisterResponse(e.getMessage(), "FAIL");
		return new ResponseEntity<RegisterResponse>(registerResponse, HttpStatus.NOT_FOUND);
	}

	private ResponseEntity<RegisterResponse> successResponse(String id, String addr) {
		User userUpdate = userRepository.findById(id).orElseThrow(() -> {
			throw new IllegalArgumentException("NOT USER");
		});

		if (addr == null || addr.isEmpty()) {
			return new ResponseEntity<RegisterResponse>(new RegisterResponse("NULL ADDR","FAIL"), HttpStatus.NOT_FOUND);
		}
		
		Date date = new Date();
		userUpdate.setAddr(addr);
		userUpdate.setUpdateDt(date);

		userRepository.save(userUpdate);

		RegisterResponse registerResponse;
		registerResponse = new RegisterResponse("UPUDATE SUCCESS", date.toString());
		return new ResponseEntity<RegisterResponse>(registerResponse, HttpStatus.OK);
	}
}
