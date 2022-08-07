package com.rohansideproject.securityapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rohansideproject.securityapp.user.model.User;
import com.rohansideproject.securityapp.user.model.repository.UserRepository;

@SpringBootApplication
public class SecurityAppApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SecurityAppApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception {
		userRepository.save(new User("Rohan", "12345"));
		userRepository.save(new User("Sita", "67890"));
		userRepository.save(new User("Zack", "123AB"));
	}

}
