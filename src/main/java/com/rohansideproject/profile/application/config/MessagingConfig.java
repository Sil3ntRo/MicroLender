package com.rohansideproject.profile.application.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rohansideproject.profile.domain.model.event.UserRegisteredEventHandler;



@Configuration
public class MessagingConfig {
	
	private static final String TOPIC = "userRegisteredTopic";
	private static final String QUEUENAME = "user.registered.profile";

	@Bean
	public Queue userRegisteredQueue() {
		return new Queue(QUEUENAME, false);
	}
	
	@Bean
	public TopicExchange userRegisteredTopic() {
		return new TopicExchange(TOPIC);
	}
	
	@Bean
	public Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with("user.#");
	}
	
	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter messageListenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(QUEUENAME);
		container.setMessageListener(messageListenerAdapter);
		return container;
	}
	
	@Bean
	public MessageListenerAdapter userRegisteredEventListener(UserRegisteredEventHandler userRegisteredEventHandler) {
		return new MessageListenerAdapter(userRegisteredEventHandler, "handleUserRegistration");
	}
}
