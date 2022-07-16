package com.rohansideproject.microlender.domain.service;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rohansideproject.microlender.application.model.LoanRequest;
import com.rohansideproject.microlender.domain.exception.UserNotFoundException;
import com.rohansideproject.microlender.domain.model.LoanApplication;
import com.rohansideproject.microlender.domain.model.User;
import com.rohansideproject.microlender.domain.repository.UserRepository;

@Component
public class LoanApplicationAdapter {
	
	private final UserRepository userRepository;
	
	@Autowired
	public LoanApplicationAdapter(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public LoanApplication transform(LoanRequest req) {
		Optional<User> userOptional = userRepository.findById(req.getBorrowerId());
		
		if(userOptional.isPresent()) {
			return new LoanApplication(req.getAmount(), userOptional.get(),
					req.getDaysToRepay(), req.getInterestRate());
 
		}
		else {
			throw new UserNotFoundException(req.getBorrowerId());
		}
	} 
}
