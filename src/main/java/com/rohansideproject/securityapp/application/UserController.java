package com.rohansideproject.securityapp.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohansideproject.securityapp.user.dto.UserDTO;
import com.rohansideproject.securityapp.user.exception.UserNotFoundException;
import com.rohansideproject.securityapp.user.model.User;
import com.rohansideproject.securityapp.user.model.repository.UserRepository;
import com.rohansideproject.securityapp.user.service.NotificationService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserRepository userRepository;
	private final NotificationService notificationService;
	
	@Autowired
	public UserController(UserRepository userRepository, NotificationService notificationService) {
		this.userRepository = userRepository;
		this.notificationService = notificationService;
	}
	
	@PostMapping("/register")
	public void register(@RequestBody UserDTO userDTO) {
		User user = new User(userDTO.getUsername(), userDTO.getPassword());
		userRepository.save(user);
		notificationService.sendMessage(userDTO);
	}
	
	@PostMapping("/validate")
	public String validateTokenAndGetUsername(@RequestHeader("Authorization") String token) {
		return userRepository.findById(token)
				.orElseThrow(() -> new UserNotFoundException()).getUsername();
	}
}
