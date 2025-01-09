package com.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringIntegrationJdbcApplication {

	public static void main(String[] args) {
		var context=SpringApplication.run(SpringIntegrationJdbcApplication.class, args);
		
		MessageChannel input=(MessageChannel)context.getBean("inputChannel");
		
		input.send(new GenericMessage<Emp>(new Emp(110,"Rahul","Mumbai",80000)));
	}

}
