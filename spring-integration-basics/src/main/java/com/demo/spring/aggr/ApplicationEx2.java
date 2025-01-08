package com.demo.spring.aggr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;

@SpringBootApplication
public class ApplicationEx2 {

	public static void main(String[] args) throws Exception{
		
		 var context=SpringApplication.run(ApplicationEx2.class,args);
		  
		  MessageChannel channel=(MessageChannel)context.getBean("inputChannel");
		  
		 channel.send(new GenericMessage<String>("We are learning splitter today"));
		
		
		  PollableChannel outchannel=(PollableChannel)context.getBean("outputChannel");
		  
		 
		  System.out.println(outchannel.receive(300).getPayload());
		  
		  
		  //Thread.sleep(5000);
		  
		   
		
		
	}

}
