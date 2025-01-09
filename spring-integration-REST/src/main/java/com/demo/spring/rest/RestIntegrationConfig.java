package com.demo.spring.rest;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpMethod;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.PollableChannel;

@Configuration
@EnableIntegration
public class RestIntegrationConfig {

	@Bean
	public MessageChannel inputChannel() {
		return new DirectChannel();
	}

	@Bean
	public PollableChannel outputChannel() {
		return new QueueChannel();
	}
	
	public MessageHandler callRestService() {
		HttpRequestExecutingMessageHandler handler=
					new HttpRequestExecutingMessageHandler("https://jsonplaceholder.typicode.com/posts/{param}");
		handler.setExtractResponseBody(true);
		handler.setExpectedResponseType(String.class);
		handler.setHttpMethod(HttpMethod.GET);
		handler.setOutputChannelName("outputChannel");
		HashMap<String, Expression> uriVariablesMap=new HashMap<>();
		SpelExpressionParser parser=new SpelExpressionParser();
		
		uriVariablesMap.put("param", parser.parseExpression("headers['myid']"));
		
		handler.setUriVariableExpressions(uriVariablesMap);
		
		return handler;
		
		
	}
	
	@Bean
	public IntegrationFlow restCallerFlow() {
		return IntegrationFlow
				.from("inputChannel")
				.handle(callRestService())
				.channel("outputChannel")
				.get();
	}
}
