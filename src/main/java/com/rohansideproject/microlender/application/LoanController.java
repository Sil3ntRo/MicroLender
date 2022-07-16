package com.rohansideproject.microlender.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rohansideproject.microlender.application.model.LoanRequest;
import com.rohansideproject.microlender.domain.model.Loan;
import com.rohansideproject.microlender.domain.model.LoanApplication;
import com.rohansideproject.microlender.domain.model.User;
import com.rohansideproject.microlender.domain.repository.LoanApplicationRepository;
import com.rohansideproject.microlender.domain.repository.LoanRepository;
import com.rohansideproject.microlender.domain.repository.UserRepository;
import com.rohansideproject.microlender.domain.service.LoanApplicationAdapter;
import com.rohansideproject.microlender.domain.service.LoanService;

@RestController
public class LoanController {
	
	private final LoanApplicationRepository loanApplicationRepository;
	private final UserRepository userRepository;
	private final LoanApplicationAdapter loanApplicationAdapter;
	private final LoanService loanService;
	
	
	@Autowired
	public LoanController(LoanApplicationRepository loanApplicationRepository, 
			UserRepository userRepository,
			LoanApplicationAdapter loanApplicationAdapter, LoanService loanService) {
		this.loanApplicationRepository = loanApplicationRepository;
		this.userRepository = userRepository;
		this.loanApplicationAdapter = loanApplicationAdapter;
		this.loanService = loanService;
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
	
	@PostMapping(value = "/loan/accept/{lenderId}/{loanApplicationId}")
	public void acceptLoan(@PathVariable final String lenderId,
							@PathVariable final String loanApplicationId) {
		loanService.acceptLoan(Long.parseLong(loanApplicationId), Long.parseLong(lenderId));
	}
	
	@GetMapping(value = "/loans")
	public List<Loan> getLoans() {
		return loanService.getLoans();
	}
}
