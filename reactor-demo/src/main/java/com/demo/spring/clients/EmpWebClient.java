package com.demo.spring.clients;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.demo.spring.dao.Emp;

public class EmpWebClient {

	public static void main(String[] args)throws Exception {
		WebClient client= WebClient.create();

		client.get()
				.uri("http://localhost:8080/emp/list")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Emp.class)
				.subscribe(emp->System.out.println(emp.getName()));
		
		Thread.sleep(Long.MAX_VALUE)	;
		
	}

}
