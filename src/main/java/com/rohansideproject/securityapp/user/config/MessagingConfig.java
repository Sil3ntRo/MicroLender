package com.rohansideproject.securityapp.user.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.ConnectionFactory;


@Configuration
public class MessagingConfig {
	
	private static final String TOPIC = "userRegisteredTopic";
	private static final String QUEUENAME = "user.registered";

	@Bean
	public Queue userRegisteredQueue() {
		return new Queue(QUEUENAME, false);
	}
	
	@Bean
	public TopicExchange userRegisteredTopic() {
		return new TopicExchange(TOPIC);
	}
	
	@Bean
	ConnectionFactory connectionFactory() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		return connectionFactory;
	}
	
	@Bean
	public Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with("user.#");
	}
}
