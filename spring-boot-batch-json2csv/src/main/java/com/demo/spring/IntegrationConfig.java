package com.demo.spring;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;

@Configuration
@EnableIntegration
public class IntegrationConfig {

	@Bean
	public FileReadingMessageSource fileReadingMessageSource() {
	    FileReadingMessageSource source = new FileReadingMessageSource();
	    source.setDirectory(new File("output"));
	    source.setFilter(new SimplePatternFileListFilter("*.csv"));
	    return source;
	}

	@Bean
	public IntegrationFlow fileInboundFlow() {
	    return IntegrationFlow.from(fileReadingMessageSource(),
	            config -> config.poller(Pollers.fixedDelay(5000)))
	            .transform(File.class, File::getAbsolutePath)
	            .handle((payload, headers) -> {
	                System.out.println("Processing file: " + payload);
	                // Add logic to process the file
	                return null;
	            })
	            .get();
	}

}
