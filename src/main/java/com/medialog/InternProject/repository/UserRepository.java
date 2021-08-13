package com.medialog.InternProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medialog.InternProject.model.User;

public interface UserRepository extends JpaRepository<User, String>{



}
