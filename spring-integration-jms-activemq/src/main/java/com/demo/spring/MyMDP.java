package com.demo.spring;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MyMDP {

	@JmsListener(destination = "myQueue",containerFactory = "listerContainer")
	public void receiveMessage(String message) {
		System.out.println("Received : "+message);
	}
}
