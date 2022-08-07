package com.rohansideproject.microlender.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rohansideproject.microlender.domain.exception.LoanApplicationNotFoundException;
import com.rohansideproject.microlender.domain.exception.LoanNotFoundException;
import com.rohansideproject.microlender.domain.exception.UserNotFoundException;
import com.rohansideproject.microlender.domain.model.Currency;
import com.rohansideproject.microlender.domain.model.Loan;
import com.rohansideproject.microlender.domain.model.LoanApplication;
import com.rohansideproject.microlender.domain.model.Money;
import com.rohansideproject.microlender.domain.model.Status;
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
	
	@Transactional
	public void repayLoan(final Money amountToRepay,
							final long loanId,
							final User borrower) {
		Loan loan = loanRepository.findOneByIdAndBorrower(loanId, borrower)
							.orElseThrow(LoanNotFoundException::new);
	
		Money actualPaidAmount = amountToRepay.getAmount() > loan.getAmountOwed().getAmount() ?
								loan.getAmountOwed() : amountToRepay;
	
		loan.repay(actualPaidAmount);;
	}

	@Transactional
	public void acceptLoan(final long loanApplicationId, final String lenderUsername) {
		User lender = findUser(lenderUsername);
		LoanApplication loanApplication = findLoanApplication(loanApplicationId);
		loanRepository.save(loanApplication.acceptLoanApplication(lender));
	}
	
	public List<Loan> findAllBorrowedLoans(final User borrower, final Status status) {
		return loanRepository.findAllByBorrowerAndStatus(borrower, status);
	}
	
	public List<Loan> findAllLentLoans(final User lender, final Status status) {
		return loanRepository.findAllByLenderAndStatus(lender, status);
	}
	
	public List<Loan> getLoans() {
		return loanRepository.findAll();
	}
	
	private User findUser(final String lenderUsername) {
		return userRepository.findById(lenderUsername).orElseThrow(() -> new UserNotFoundException(lenderUsername));
	}
	
	private LoanApplication findLoanApplication(final long loanApplicationId) {
		return loanApplicationRepository.findById(loanApplicationId)
				.orElseThrow(() -> new LoanApplicationNotFoundException(loanApplicationId));
	}
	
}
