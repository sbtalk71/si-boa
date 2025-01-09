package com.demo.spring.soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

@SpringBootApplication
public class SaopApplication {

	public static void main(String[] args) throws Exception {
		var context = SpringApplication.run(SaopApplication.class, args);

		MessageChannel channel = (MessageChannel) context.getBean("inputChannel");
		
		String req="""
				<q0:add xmlns:q0="http://ws.demo.com/">
      <arg0>105</arg0>
      <arg1>20</arg1>
    </q0:add>
				""";
		Message<String> msg = MessageBuilder.withPayload(req).build();

		channel.send(msg);
		

		PollableChannel outchannel = (PollableChannel) context.getBean("outputChannel");

		System.out.println(outchannel.receive(300).getPayload());

		Thread.sleep(5000);
	}

}
