package com.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		var context=SpringApplication.run(Application.class, args);
		
		  
		  MessageChannel channel=(MessageChannel)context.getBean("inputChannel");
		  
		  
		  
		  Message<String> msg=MessageBuilder .withPayload("Hello") .build();
		  
		  
		  channel.send(msg);
		  
		
	}

}
