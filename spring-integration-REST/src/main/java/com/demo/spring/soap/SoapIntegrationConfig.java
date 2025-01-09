package com.demo.spring.soap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.ws.SimpleWebServiceOutboundGateway;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@EnableIntegration
public class SoapIntegrationConfig {

	@Bean
	public MessageChannel inputChannel() {
		return new DirectChannel();
	}

	@Bean
	public PollableChannel outputChannel() {
		return new QueueChannel();
	}

	public SimpleWebServiceOutboundGateway soapGateway() {
		/*
		 * SimpleWebServiceOutboundGateway gateway = new
		 * SimpleWebServiceOutboundGateway(
		 * "https://www.w3schools.com/xml/tempconvert.asmx/FahrenheitToCelsius");
		 */

		SimpleWebServiceOutboundGateway gateway = new SimpleWebServiceOutboundGateway(
				"http://localhost:8181/cal");
		return gateway;
	}
	
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller= new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(FahrenheitToCelsius.class);
		return marshaller;
	}
	
	@Bean
	public IntegrationFlow wsFlow() {
		return IntegrationFlow
				.from("inputChannel")
				.handle(soapGateway())
				.channel("outputChannel")
				.get();
	}
	
}
