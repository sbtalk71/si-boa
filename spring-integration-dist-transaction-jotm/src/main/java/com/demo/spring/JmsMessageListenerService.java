package com.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class JmsMessageListenerService {

    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    @JmsListener(destination = "myqueue", containerFactory = "jmsListenerContainerFactory")
    public void onMessage(String message) {
    	System.out.println("Received : "+message);
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessageContent(message);
        messageRepository.save(messageEntity);  // Save message to H2 DB
    }
}
