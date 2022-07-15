package com.rohansideproject.microlender.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rohansideproject.microlender.domain.model.LoanRequest;
import com.rohansideproject.microlender.domain.repository.LoanRequestRepository;

@RestController
public class LoanController {
	
	private final LoanRequestRepository loanRequestRepository;
	
	@Autowired
	public LoanController(LoanRequestRepository loanRequestRepository) {
		this.loanRequestRepository = loanRequestRepository;
	}
	
	@PostMapping(value = "/loan/request")
	public void requestLoan(@RequestBody final LoanRequest loanRequest) {
		System.out.println(loanRequest);
	}
}
