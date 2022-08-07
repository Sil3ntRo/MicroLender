package com.rohansideproject.microlender.application.service;

import com.rohansideproject.microlender.domain.model.User;

public interface TokenValidationService {
	
	User validateTokenAndGetUser(final String token);
}
