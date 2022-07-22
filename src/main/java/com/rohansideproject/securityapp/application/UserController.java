package com.rohansideproject.securityapp.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohansideproject.securityapp.user.exception.UserNotFoundException;
import com.rohansideproject.securityapp.user.model.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostMapping("/validate")
	public String validateTokenAndGetUsername(@RequestHeader("Authorization") String token) {
		return userRepository.findById(token)
				.orElseThrow(() -> new UserNotFoundException()).getUsername();
	}
}
