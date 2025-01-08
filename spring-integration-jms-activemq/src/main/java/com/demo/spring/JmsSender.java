package com.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;

//@Component
public class JmsSender implements CommandLineRunner {

	@Autowired
	JmsTemplate jmsTemplate;

	@Override
	public void run(String... args) throws Exception {
		jmsTemplate.send(new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = session.createTextMessage("Message from SI");
				return message;
			}
		});

	}

}
