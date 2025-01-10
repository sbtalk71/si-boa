package com.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@Autowired
	AppGateway gateway;
	
	@GetMapping("/api/secured")
	public String securedResource() {
		gateway.send("Hello");
		return "secured message Sent";
	}
	
	@GetMapping("/normal")
	public String normalResource() {
		
		return "Normal message Sent";
	}
}
