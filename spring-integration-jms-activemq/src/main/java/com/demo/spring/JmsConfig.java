package com.demo.spring;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import jakarta.jms.ConnectionFactory;

@Configuration
@EnableJms
public class JmsConfig {

	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory cf= new ActiveMQConnectionFactory();
		cf.setBrokerURL("tcp://localhost:61616");
		return cf;
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jt= new JmsTemplate();
		jt.setConnectionFactory(connectionFactory());
		jt.setDefaultDestinationName("myQueue");
		return jt;
	}
	
	@Bean
	public DefaultJmsListenerContainerFactory listerContainer() {
		DefaultJmsListenerContainerFactory lc=new DefaultJmsListenerContainerFactory();
		lc.setConnectionFactory(connectionFactory());
		
		return lc;
	}
}
