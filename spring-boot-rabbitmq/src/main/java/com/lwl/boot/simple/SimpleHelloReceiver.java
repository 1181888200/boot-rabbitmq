package com.lwl.boot.simple;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "simple")
public class SimpleHelloReceiver {
	
	@RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1  : " + hello);
    }
	
}
