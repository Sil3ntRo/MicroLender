package com.rohansideproject.microlender.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohansideproject.microlender.domain.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
