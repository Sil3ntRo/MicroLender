package com.rohansideproject.microlender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.rohansideproject.microlender.application.service.TokenValidationService;
import com.rohansideproject.microlender.domain.exception.UserNotFoundException;
import com.rohansideproject.microlender.domain.model.User;
import com.rohansideproject.microlender.domain.repository.UserRepository;

@Profile("test")
@Primary
@Component
public class TestTokenValidationService implements TokenValidationService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public TestTokenValidationService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User validateTokenAndGetUser(String token) {
		return userRepository.findById(token)
				.orElseThrow(() -> new UserNotFoundException(token));
	}
}
