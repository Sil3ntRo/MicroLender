package com.rohansideproject.securityapp.user.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohansideproject.securityapp.user.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
}
