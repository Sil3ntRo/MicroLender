package com.rohansideproject.microlender.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohansideproject.microlender.domain.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
