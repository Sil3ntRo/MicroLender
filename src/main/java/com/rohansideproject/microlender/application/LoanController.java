package com.rohansideproject.microlender.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rohansideproject.microlender.application.model.LoanRequest;
import com.rohansideproject.microlender.domain.model.LoanApplication;
import com.rohansideproject.microlender.domain.model.User;
import com.rohansideproject.microlender.domain.repository.LoanApplicationRepository;
import com.rohansideproject.microlender.domain.repository.UserRepository;
import com.rohansideproject.microlender.domain.service.LoanApplicationAdapter;

@RestController
public class LoanController {
	
	private final LoanApplicationRepository loanApplicationRepository;
	private final UserRepository userRepository;
	private final LoanApplicationAdapter loanApplicationAdapter;
	
	@Autowired
	public LoanController(LoanApplicationRepository loanApplicationRepository, 
			UserRepository userRepository,
			LoanApplicationAdapter loanApplicationAdapter) {
		this.loanApplicationRepository = loanApplicationRepository;
		this.userRepository = userRepository;
		this.loanApplicationAdapter = loanApplicationAdapter;
	}
	
	@PostMapping(value = "/loan/request")
	public void requestLoan(@RequestBody final LoanRequest loanRequest) {
		loanApplicationRepository.save(loanApplicationAdapter.transform(loanRequest));
	}
	
	@GetMapping(value = "/loan/requests")
	public List<LoanApplication> findAllLoanApplications() {
		return loanApplicationRepository.findAll();
	}
	
	@GetMapping(value = "/users")
	public List<User> findUsers() {
		return userRepository.findAll();
	}
}
