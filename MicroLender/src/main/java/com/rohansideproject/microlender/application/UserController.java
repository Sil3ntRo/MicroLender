package com.rohansideproject.microlender.application;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohansideproject.microlender.application.service.TokenValidationService;
import com.rohansideproject.microlender.application.service.impl.TokenValidationServiceImpl;
import com.rohansideproject.microlender.domain.model.User;
import com.rohansideproject.microlender.domain.repository.UserRepository;

@RestController
public class UserController {
	
	private final TokenValidationService tokenValidationService;
	private final UserRepository userRepository;
	
	@Autowired
	public UserController(TokenValidationService tokenValidationService, UserRepository userRepository) {
		this.tokenValidationService = tokenValidationService;
		this.userRepository = userRepository;
	}

	@GetMapping(value = "/users")
	public List<User> findUsers(HttpServletRequest request) {
		tokenValidationService.validateTokenAndGetUser(request.getHeader(request.getHeader(HttpHeaders.AUTHORIZATION)));
		return userRepository.findAll();
	}
	
}
