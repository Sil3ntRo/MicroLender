package com.rohansideproject.microlender.domain.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String userName) {
		super("User with id: " + userName + " not found.");
	}
	
}
