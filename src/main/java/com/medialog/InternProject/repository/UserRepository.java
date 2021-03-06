package com.medialog.InternProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.medialog.InternProject.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	@Query(value = "select * from new_user_tb order by reg_dt desc", nativeQuery=true)
	public List<User> findAll();



}
