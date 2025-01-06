package org.demo.spring.clients;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import com.demo.spring.dao.Emp;

public class Client2 {

	public static void main(String[] args) {
		RestClient client= RestClient.create();
		
	String resp=client
		.get()
		.uri("http://localhost:8080/emp/100")
		.accept(MediaType.APPLICATION_JSON)
		.retrieve()
		.body(String.class);
	
	System.out.println(resp);
	
	
	Emp emp=client
			.get()
			.uri("http://localhost:8080/emp/100")
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.body(Emp.class);
		
		System.out.println(emp.getName());
		
		
	String resp2=client
				.post()
				.uri("http://localhost:8080/emp/add")
				.body(new Emp(100, "A", "B", 10000))
				.accept(MediaType.TEXT_PLAIN)
				.contentType(MediaType.APPLICATION_JSON)
				.retrieve()
				.body(String.class);

	System.out.println(resp2);
	}

}
