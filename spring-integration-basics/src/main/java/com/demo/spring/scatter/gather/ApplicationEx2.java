package com.demo.spring.scatter.gather;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;

@SpringBootApplication
public class ApplicationEx2 {

	public static void main(String[] args) throws Exception{
		
		 var context=SpringApplication.run(ApplicationEx2.class,args);
		 
		 SubscribableChannel outChannel=(SubscribableChannel)context.getBean("outputChannel");
		 
		 outChannel.subscribe(message->{
			 List<Message<String>> messageList=(List<Message<String>>)message.getPayload();
			 
			 messageList.stream().forEach(m->System.out.println(m.getPayload()));
		 });
		  
		 
		 MessageChannel channel=(MessageChannel)context.getBean("inputChannel");
		  
		 channel.send(new GenericMessage<String>("iphone 16"));
		
		
		  
		   
		
		
	}

}
