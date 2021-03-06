package com.medialog.InternProject.security;

public interface TokenProvider {
	String createToken(String subject);

	String getSubject(String token);

	boolean validateToken(String token);
}