package com.rohansideproject.microlender.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohansideproject.microlender.domain.model.LoanApplication;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {

}
