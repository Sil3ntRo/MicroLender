package com.rohansideproject.microlender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rohansideproject.microlender.domain.model.User;
import com.rohansideproject.microlender.domain.repository.UserRepository;

@SpringBootApplication
public class MicroLenderApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MicroLenderApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception {
		userRepository.save(new User(1, "John", "B", 27, "Software Engineer" ));
		userRepository.save(new User(2, "Peter", "C", 24, "Pilot" ));
		userRepository.save(new User(3, "Zack", "E", 23, "Unemployed" ));
	}
}
