package com.medialog.InternProject.network;

import java.util.List;

import com.medialog.InternProject.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListResponse {
	
	private String message;
	    
	private List<User> result;
	    
	
}
