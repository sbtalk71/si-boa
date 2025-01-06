package org.demo.spring.clients;

import org.springframework.web.client.RestTemplate;

public class Client {

	public static void main(String[] args) {
		RestTemplate rt= new RestTemplate();
		
		String data=rt.getForObject("http://localhost:8080/emp/100", String.class);
		
		System.out.println(data);
		
		

	}

}
