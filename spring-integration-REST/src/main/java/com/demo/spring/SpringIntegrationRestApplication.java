package com.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

@SpringBootApplication
public class SpringIntegrationRestApplication {

	public static void main(String[] args) throws Exception{
		var context=SpringApplication.run(SpringIntegrationRestApplication.class, args);
		
		 MessageChannel channel=(MessageChannel)context.getBean("inputChannel");
		  
		 Message<String> message=MessageBuilder.withPayload("").setHeader("myid", "110").build();
		
		 
		 channel.send(message);
		
		
		  PollableChannel outchannel=(PollableChannel)context.getBean("outputChannel");
		  
		 
		  System.out.println(outchannel.receive(300).getPayload());
		  
		  
		  Thread.sleep(5000);
	}

}
