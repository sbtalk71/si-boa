package com.demo.spring.ex1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MessageAppRunner implements CommandLineRunner {

	@Autowired
	DemoGatway gateway;
	
	@Override
	public void run(String... args) throws Exception {
		gateway.getMessage("Amit Kumar");

	}

}
