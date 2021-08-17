package com.medialog.InternProject.controller;

import java.text.ParseException;
import java.util.Date;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medialog.InternProject.network.RegisterResponse;
import com.medialog.InternProject.service.UpdateUserService;

@RestController
public class UpdateUserController {

	@Autowired
	private UpdateUserService updateUserService;

	@PutMapping("/user/update/{id}")
	public ResponseEntity<RegisterResponse> updateUser(@PathVariable String id, @RequestBody String addr)
			throws ParseException {
		try {
			return successResponse(id, addr);
		} catch (IllegalArgumentException e) {
			return updateException(e);
		} catch (JSONException e) {
			return jsonException(e);
		}

	}

	private ResponseEntity<RegisterResponse> updateException(IllegalArgumentException e) {
		RegisterResponse registerResponse;
		registerResponse = new RegisterResponse(e.getMessage(), "FAIL");
		return new ResponseEntity<RegisterResponse>(registerResponse, HttpStatus.NOT_FOUND);
	}
	
	private ResponseEntity<RegisterResponse> jsonException(JSONException e) {
		RegisterResponse registerResponse;
		registerResponse = new RegisterResponse(e.getMessage(), "FAIL");
		return new ResponseEntity<RegisterResponse>(registerResponse, HttpStatus.NOT_FOUND);
	}

	private ResponseEntity<RegisterResponse> successResponse(String id, String addr) {
        Date date = updateUserService.update(id, addr);
        
		RegisterResponse registerResponse;
		registerResponse = new RegisterResponse("UPUDATE SUCCESS", date.toString());
		return new ResponseEntity<RegisterResponse>(registerResponse, HttpStatus.OK);
	}
}
