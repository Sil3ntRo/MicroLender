package com.rohansideproject.microlender.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohansideproject.microlender.domain.model.Loan;
import com.rohansideproject.microlender.domain.model.Status;
import com.rohansideproject.microlender.domain.model.User;

public interface LoanRepository extends JpaRepository<Loan, Long> {
	
	List<Loan> findAllByBorrowerAndStatus(User borrower, Status status);
	List<Loan> findAllByLenderAndStatus(User lender, Status status);
	Optional<Loan> findOneByIdAndBorrower(Long id, User borrower);
}
