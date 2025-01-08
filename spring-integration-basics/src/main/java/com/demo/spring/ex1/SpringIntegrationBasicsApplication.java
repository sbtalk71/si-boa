package com.demo.spring.ex1;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

@SpringBootApplication
public class SpringIntegrationBasicsApplication {

	public static void main(String[] args) throws Exception{
		
		 var context=SpringApplication.run(SpringIntegrationBasicsApplication.class,args);
		 /* 
		 * MessageChannel channel=(MessageChannel)context.getBean("inputChannel");
		 * 
		 * 
		 * 
		 * Message<String> msg=MessageBuilder .withPayload("Hello") .build();
		 * 
		 * 
		 * channel.send(msg);
		 */
		
		/*
		 * PollableChannel outchannel=(PollableChannel)context.getBean("outputChannel");
		 * 
		 * System.out.println(outchannel.receive(3000));
		 */
		  
		  //Thread.sleep(5000);
		  
		   
		
		
	}

}
