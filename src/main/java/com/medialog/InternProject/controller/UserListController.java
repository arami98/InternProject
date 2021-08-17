package com.medialog.InternProject.controller;

import java.util.List;

import com.medialog.InternProject.network.UserListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<UserListResponse> selectAll() {
	      List<User> list = userRepository.findAll();
	      
	      UserListResponse userlistResponse = new UserListResponse();
	      
	      if(list.isEmpty()){
	         userlistResponse.setMessage("Empty List");
	         userlistResponse.setResult(null);
	         return new ResponseEntity<UserListResponse>(userlistResponse,new HttpHeaders(), HttpStatus.NOT_FOUND);
	      }
	      
	      else{
	         userlistResponse.setMessage("Success");
	         userlistResponse.setResult(list);
	      }
	      
	      return ResponseEntity.ok(userlistResponse);
	   }


}
