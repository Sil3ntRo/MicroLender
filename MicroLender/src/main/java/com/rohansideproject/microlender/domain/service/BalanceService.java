package com.rohansideproject.microlender.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rohansideproject.microlender.domain.exception.UserNotFoundException;
import com.rohansideproject.microlender.domain.model.Money;
import com.rohansideproject.microlender.domain.model.User;
import com.rohansideproject.microlender.domain.repository.UserRepository;

@Component
public class BalanceService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public BalanceService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Transactional
	public void topUpBalance(final Money money, String authToken) {
		User user = findUser(authToken);
		user.topUp(money);
	}
	
	@Transactional
	public void withdrawFromBalance(final Money money, String authToken) {
		User user = findUser(authToken);
		user.withDraw(money);
	}
	
	private User findUser(String authToken) {
		User user = userRepository.findById(authToken)
						.orElseThrow(() -> new UserNotFoundException(authToken));
		return user;
	}
}
