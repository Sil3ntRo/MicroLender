package com.rohansideproject.microlender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rohansideproject.microlender.domain.model.Balance;
import com.rohansideproject.microlender.domain.model.Currency;
import com.rohansideproject.microlender.domain.model.Money;
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
		User rohan = new User("Rohan", "Rohan", "B", 27, "Software Engineer", new Balance());
		User sita = new User("Sita", "Sita", "C", 24, "Pilot", new Balance());
		User zack = new User("Zack", "Zack", "E", 23, "Unemployed", new Balance());
		rohan.topUp(new Money(1500, Currency.USD));
		sita.topUp(new Money(2000, Currency.USD));
		zack.topUp(new Money(750, Currency.USD));
		userRepository.save(rohan);
		userRepository.save(sita);
		userRepository.save(zack);
	}
}
