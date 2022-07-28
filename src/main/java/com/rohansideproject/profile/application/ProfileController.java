package com.rohansideproject.profile.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohansideproject.profile.domain.model.User;
import com.rohansideproject.profile.domain.model.repository.UserRepository;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	private final UserRepository userRepository;
	
	@Autowired
	public ProfileController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/users")
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping("/user")
	public void newUser(@RequestBody final User user) {
		userRepository.save(user);
	}
	
	@PutMapping("/user")
	public void updateUser(@RequestBody final User user) {
		userRepository.save(user);
	}
}
