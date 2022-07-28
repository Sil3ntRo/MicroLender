package com.rohansideproject.profile;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rohansideproject.profile.domain.model.User;
import com.rohansideproject.profile.domain.model.repository.UserRepository;

@SpringBootApplication
public class ProfileApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProfileApplication.class, args);
	}
	
	@Override
	public void run(String...args) throws Exception {
		userRepository.save(new User("Rohan", "Rohan", "Gayle", 31, "Software Engineer", LocalDate.now()));
	}

}
