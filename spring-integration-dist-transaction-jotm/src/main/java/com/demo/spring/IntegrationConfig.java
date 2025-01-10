package com.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.jms.ConnectionFactory;

//@Configuration
public class IntegrationConfig {

    @Autowired
    private JtaTransactionManager jtaTransactionManager;

    @Autowired
    private ConnectionFactory jmsConnectionFactory;
    
    

    @Bean
    public IntegrationFlow jmsInboundFlow() {
        return IntegrationFlows.from(Jms.messageDrivenChannelAdapter(jmsConnectionFactory).destination("myqueue"))
                
                //.transactional(jtaTransactionManager))  // Enable transactional handling
            .handle((payload, headers) -> {
                // Process message here (save to DB)
                saveMessageToDb((String) payload);
                return null;
            })
            .get();
    }

    private void saveMessageToDb(String message) {
        // Code to save the message to the H2 database
    }
    
   
}
