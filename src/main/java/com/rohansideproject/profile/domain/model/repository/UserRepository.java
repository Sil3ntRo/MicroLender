package com.rohansideproject.profile.domain.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohansideproject.profile.domain.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
