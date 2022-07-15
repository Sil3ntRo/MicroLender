package com.rohansideproject.microlender.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohansideproject.microlender.domain.model.LoanRequest;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {

}
