package com.demo.spring.ex2;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;

@SpringBootApplication
public class ApplicationEx2 {

	public static void main(String[] args) throws Exception{
		
		 var context=SpringApplication.run(ApplicationEx2.class,args);
		  
		  MessageChannel channel=(MessageChannel)context.getBean("inputChannel");
		  
		  
		  channel.send(new GenericMessage<Order>(new Order("electronics","iphone 16")) );
		  channel.send(new GenericMessage<Order>(new Order("electronics","iphone 16")) );
		  channel.send(new GenericMessage<Order>(new Order("apparel","Nike T shirt")) );
		  channel.send(new GenericMessage<Order>(new Order("furniture","table")) );
		  channel.send(new GenericMessage<Order>(new Order("furniture","chair")) );
		 
		
		/*
		 * PollableChannel outchannel=(PollableChannel)context.getBean("outputChannel");
		 * 
		 * System.out.println(outchannel.receive(3000));
		 */
		  
		  //Thread.sleep(5000);
		  
		   
		
		
	}

}
