package com.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

	@Autowired
	Performer performer;

	@Override
	public void run(String... args) throws Exception {
		performer.perform();
		
		System.out.println(performer.getClass().getName());

	}

}
