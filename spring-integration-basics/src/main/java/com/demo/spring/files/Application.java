package com.demo.spring.files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.PollableChannel;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception{
		
		 var context=SpringApplication.run(Application.class,args);
		 
		
		  PollableChannel outchannel=(PollableChannel)context.getBean("outputChannel");
		  
		  System.out.println(outchannel.receive(3000));
		 
		  
		  //Thread.sleep(5000);
		  
		   
		
		
	}

}
