package com.lwl.boot.simple;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleHelloSender2 {

	 @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String sendMsg) {
        System.out.println("Sender2 : " + sendMsg);
        this.rabbitTemplate.convertAndSend("simple", sendMsg);
    }
	
}
