package com.demo.spring.files;

import java.io.File;
import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

@Configuration
@EnableIntegration
public class FileInboundConfig {

		
	@Bean
	public PollableChannel outputChannel() {
		return new QueueChannel();
	}
	
	@Bean
	public IntegrationFlow fileInputFlow() {
		return IntegrationFlow
				.from(Files.inboundAdapter(new File("input"))
						.autoCreateDirectory(false)
						.preventDuplicates(false)
						.patternFilter("*.txt"),e->e.poller(Pollers.fixedDelay(Duration.ofSeconds(5))))
				.transform(new FileToStringTransformer())
				.handle(m->System.out.println(m.getPayload()))
				.get();
	}
}
