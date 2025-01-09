package com.demo.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.router.ExpressionEvaluatingRouter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;

import java.io.File;

@Configuration
@EnableIntegration
public class LoanBrokerIntegrationConfig {

	@Bean
	public MessageChannel loanRequestsChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel approvalChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel rejectionChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public MessageChannel decisionRouterChannel() {
		return new DirectChannel();
	}

	@Bean
	public FileReadingMessageSource fileReadingMessageSource() {
		FileReadingMessageSource source = new FileReadingMessageSource();
		source.setDirectory(new File("loan-requests"));
		// source.setFilter(new SimplePatternFileListFilter("*.txt"));
		return source;
	}
	/*
	 * @Bean public IntegrationFlow fileReadingFlow() { return IntegrationFlow
	 * .from(Files.inboundAdapter(new File("input")) // .autoCreateDirectory(true)
	 * .patternFilter("*.txt"), e -> e.poller(Pollers.fixedDelay(1000)))
	 * .transform(Files.toStringTransformer()) .channel("loanRequestsChannel")
	 * .get(); }
	 */

	@Bean
	public IntegrationFlow loanRequestFlow() {
		return IntegrationFlow.from(fileReadingMessageSource(), c -> c.poller(p -> p.fixedDelay(1000)))
				.transform(File::toString).channel("loanRequestsChannel").get();
	}

	@Bean
	@Router(inputChannel = "decisionRouterChannel")
	public ExpressionEvaluatingRouter decisionRouter() {
		ExpressionEvaluatingRouter router = new ExpressionEvaluatingRouter("payload.approved");
		router.setDefaultOutputChannelName("rejectionChannel");
		router.setChannelMapping("true", "approvalChannel");
		return router;
	}
}
