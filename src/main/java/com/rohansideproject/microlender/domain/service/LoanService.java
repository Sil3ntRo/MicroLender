package com.rohansideproject.microlender.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rohansideproject.microlender.domain.exception.LoanApplicationNotFoundException;
import com.rohansideproject.microlender.domain.exception.UserNotFoundException;
import com.rohansideproject.microlender.domain.model.Loan;
import com.rohansideproject.microlender.domain.model.LoanApplication;
import com.rohansideproject.microlender.domain.model.User;
import com.rohansideproject.microlender.domain.repository.LoanApplicationRepository;
import com.rohansideproject.microlender.domain.repository.LoanRepository;
import com.rohansideproject.microlender.domain.repository.UserRepository;

@Component
public class LoanService {
	
	private final LoanApplicationRepository loanApplicationRepository;
	private final UserRepository userRepository;
	private final LoanRepository loanRepository;
	
	@Autowired
	public LoanService(LoanApplicationRepository loanApplicationRepository, UserRepository userRepository,
			LoanRepository loanRepository) {
		this.loanApplicationRepository = loanApplicationRepository;
		this.userRepository = userRepository;
		this.loanRepository = loanRepository;
	}



	public void acceptLoan(final long loanApplicationId, final String lenderUsername) {
		User lender = userRepository.findById(lenderUsername).orElseThrow(() -> new UserNotFoundException(lenderUsername));
		LoanApplication loanApplication = loanApplicationRepository.findById(loanApplicationId)
				.orElseThrow(() -> new LoanApplicationNotFoundException(loanApplicationId));
		loanRepository.save(new Loan(lender, loanApplication));
	}
	
	public List<Loan> getLoans() {
		return loanRepository.findAll();
	}
	
}
