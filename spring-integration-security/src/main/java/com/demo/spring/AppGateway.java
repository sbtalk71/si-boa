package com.demo.spring;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.security.access.annotation.Secured;

@MessagingGateway
public interface AppGateway {

	@Gateway(requestChannel = "securedChannel")
	@Secured("ROLE_ADMIN")
	public void send(String payload);
}
