package com.medialog.InternProject.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<IdCheckResponse> idCheck(@PathVariable String id) {
		IdCheckResponse idCheckRes = new IdCheckResponse();
		boolean flag = userRepository.existsById(id);
		String regExp = ".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*";

		//아이디 길이 체크
		if(id.length() > 45) {
			idCheckRes.setMessage("ID LENGTH IS TOO LONG");
			idCheckRes.setResult("Fail");
			return new ResponseEntity<IdCheckResponse>(idCheckRes,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		
		//아이디 형식 체크
		if(id.matches(regExp)) {
			idCheckRes.setMessage("ID FORM ERROR");
			idCheckRes.setResult("Fail");
			return new ResponseEntity<IdCheckResponse>(idCheckRes,new HttpHeaders(),HttpStatus.NOT_FOUND);
		}
		
		
		//아이디 사용 여부 확인
		if(flag == true) {
			idCheckRes.setMessage("이미 사용중인 id입니다.");
			idCheckRes.setResult("UNUSABLE");
			 return new ResponseEntity<IdCheckResponse>(idCheckRes,new HttpHeaders(), HttpStatus.OK);
		}
		else {
			idCheckRes.setMessage("사용 가능한 id입니다.");
			idCheckRes.setResult("USABLE");
			 return new ResponseEntity<IdCheckResponse>(idCheckRes,new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	@Getter
	@Setter
	static class IdCheckResponse{
		private String result;
		private String message;
	}
}
