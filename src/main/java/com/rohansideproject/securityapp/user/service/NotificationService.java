package com.rohansideproject.securityapp.user.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.rohansideproject.securityapp.user.dto.UserDTO;
import com.rohansideproject.securityapp.user.model.User;

@Component
public class NotificationService {
	
	private final RabbitTemplate rabbitTemplate;
	private final static Gson GSON = new Gson();
	
	@Autowired
	public NotificationService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void sendMessage(UserDTO userDTO) {
		userDTO.setPassword(null);
		rabbitTemplate.convertAndSend("userRegisteredTopic", "user.registered", GSON.toJson(userDTO));
	}
}
