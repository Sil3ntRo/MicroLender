package com.rohansideproject.microlender.domain.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.rohansideproject.microlender.domain.model.User;
import com.rohansideproject.microlender.domain.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventHandler {
	
	private Logger LOGGER = LoggerFactory.getLogger(UserRegisteredEventHandler.class);
	private static final Gson GSON = new Gson();
	private UserRepository userRepository;
	
	@Autowired
	public void UserRegisteredEventHandler(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void handleUserRegistration(String userDetails) {
		User user = GSON.fromJson(userDetails, User.class);
		LOGGER.info("user {} registered", user.getUsername());
		userRepository.save(user);
	}
}
