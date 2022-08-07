package com.rohansideproject.microlender.application;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.rohansideproject.microlender.application.model.LoanRepaymentRequest;
import com.rohansideproject.microlender.application.model.LoanRequest;
import com.rohansideproject.microlender.application.service.TokenValidationService;
import com.rohansideproject.microlender.application.service.impl.TokenValidationServiceImpl;
import com.rohansideproject.microlender.domain.model.Loan;
import com.rohansideproject.microlender.domain.model.LoanApplication;
import com.rohansideproject.microlender.domain.model.Status;
import com.rohansideproject.microlender.domain.model.User;
import com.rohansideproject.microlender.domain.repository.LoanApplicationRepository;
import com.rohansideproject.microlender.domain.repository.LoanRepository;
import com.rohansideproject.microlender.domain.repository.UserRepository;
import com.rohansideproject.microlender.domain.service.LoanApplicationAdapter;
import com.rohansideproject.microlender.domain.service.LoanService;

@RestController
public class LoanController {
	
	private final LoanApplicationRepository loanApplicationRepository;
	private final LoanApplicationAdapter loanApplicationAdapter;
	private final LoanService loanService;
	private final TokenValidationService tokenValidationService;
	
	
	@Autowired
	public LoanController(LoanApplicationRepository loanApplicationRepository, 
			LoanApplicationAdapter loanApplicationAdapter, LoanService loanService,
			TokenValidationService tokenValidationService) {
		this.loanApplicationRepository = loanApplicationRepository;
		this.loanApplicationAdapter = loanApplicationAdapter;
		this.loanService = loanService;
		this.tokenValidationService = tokenValidationService;
	}
	
	@PostMapping(value = "/loan/request")
	public void requestLoan(@RequestBody final LoanRequest loanRequest, HttpServletRequest request) {
		User borrower = tokenValidationService.validateTokenAndGetUser(request.getHeader(request.getHeader(HttpHeaders.AUTHORIZATION)));
		
		loanApplicationRepository.save(loanApplicationAdapter.transform(loanRequest, borrower));
	}
	
	@GetMapping(value = "/loan/requests")
	public List<LoanApplication> findAllLoanApplications(HttpServletRequest request) {
		return loanApplicationRepository.findAllByStatusEquals(Status.ONGOING);
	}
	
	@GetMapping(value = "/loan/{status}/borrowed")
	public List<Loan> finaBorrowedLoan(@RequestHeader String authorization,
										@PathVariable Status status) {
		User borrower = tokenValidationService.validateTokenAndGetUser(authorization);
		return loanService.findAllBorrowedLoans(borrower, status);
	}
	
	@GetMapping(value = "/loan/{status}/lent")
	public List<Loan> findLentLoans(@RequestHeader String authorization,
									@PathVariable Status status) {
		User lender = tokenValidationService.validateTokenAndGetUser(authorization);
		return loanService.findAllLentLoans(lender, status);
	}
	
	@PostMapping(value = "/loan/repay")
	public void repayLoan(@RequestBody LoanRepaymentRequest request,
							@RequestHeader String authorization) {
		User borrower = tokenValidationService.validateTokenAndGetUser(authorization);
		loanService.repayLoan(request.getAmount(), request.getLoanId(), borrower);
	}
	
	
	@PostMapping(value = "/loan/accept/{loanApplicationId}")
	public void acceptLoan(
							@PathVariable final String loanApplicationId,
							HttpServletRequest request) {
		User lender = tokenValidationService.validateTokenAndGetUser(request.getHeader(request.getHeader(HttpHeaders.AUTHORIZATION)));
		loanService.acceptLoan(Long.parseLong(loanApplicationId), lender.getUsername());
	}
	
	@GetMapping(value = "/loans")
	public List<Loan> getLoans() {
		return loanService.getLoans();
	}
}
