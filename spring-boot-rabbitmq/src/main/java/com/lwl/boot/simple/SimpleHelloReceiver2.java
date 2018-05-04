package com.lwl.boot.simple;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "simple")
public class SimpleHelloReceiver2 {
	
	@RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver2  : " + hello);
    }
	
}
