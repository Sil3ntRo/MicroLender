package com.rohansideproject.microlender.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohansideproject.microlender.domain.model.LoanApplication;
import com.rohansideproject.microlender.domain.model.Status;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
	
	List<LoanApplication> findAllByStatusEquals(Status status);
}
