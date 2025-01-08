package com.demo.spring.ex1;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface DemoGatway {

	@Gateway(requestChannel = "inputChannel")
	public void getMessage(String message);
}
