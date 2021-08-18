package com.medialog.InternProject.service;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medialog.InternProject.model.User;
import com.medialog.InternProject.repository.UserRepository;

@Service
public class UpdateUserServiceImpl implements UpdateUserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public Date update(String id, String addr) throws IllegalArgumentException {
		User userUpdate = checkUser(id);
		checkAddr(addr);

		Date date = new Date();
		userUpdate.setAddr(addr);
		userUpdate.setUpdateDt(date);

		userRepository.save(userUpdate);
		return date;
	}

	private User checkUser(String id) throws IllegalArgumentException {
		User userUpdate = userRepository.findById(id).orElseThrow(() -> {
			throw new IllegalArgumentException("NOT USER");
		});

		return userUpdate;
	}

	private void checkAddr(String addr) throws IllegalArgumentException {
		try {
			JSONObject jObject = new JSONObject(addr);
			String city = jObject.getString("city");
			String code = jObject.getString("code");
			String dong = jObject.getString("dong");
			String country = jObject.getString("country");

			if (city.isEmpty() || code.isEmpty() || dong.isEmpty() || country.isEmpty()) {
				throw new IllegalArgumentException("NULL ADDRESS");
			}
		} catch (JSONException e) {
			throw new JSONException("JSON PASING");
		}
	}

}
