package com.demo.spring.scatter.gather;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

@Configuration
@EnableIntegration
public class ScatterGatherConfig {

	@Bean
	public MessageChannel inputChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel aChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel bChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel cChannel() {
		return new DirectChannel();
	}

	@Bean
	public SubscribableChannel outputChannel() {
		return new PublishSubscribeChannel();
	}

	@Bean
	public IntegrationFlow scatterGatherFlow() {

		return IntegrationFlow.from("inputChannel")
				.scatterGather(
						scatterer -> scatterer.applySequence(true).recipient("aChannel").recipient("bChannel")
								.recipient("cChannel"),
						gatherer -> gatherer.releaseStrategy(group -> group.size() == 3)
								.outputProcessor(messageGroup -> messageGroup.getMessages().stream().toList()))
				.channel("outputChannel").get();
	}

	@Bean
	public IntegrationFlow aChannelProcessing() {
		return IntegrationFlow
				.from("aChannel")
				.handle((payload,headers)->"Processed at aChannel : "+payload)
				.get();
	}
	
	@Bean
	public IntegrationFlow bChannelProcessing() {
		return IntegrationFlow
				.from("bChannel")
				.handle((payload,headers)->"Processed at bChannel : "+payload)
				.get();
	}
	
	@Bean
	public IntegrationFlow cChannelProcessing() {
		return IntegrationFlow
				.from("cChannel")
				.handle((payload,headers)->"Processed at cChannel : "+payload)
				.get();
	}

}
