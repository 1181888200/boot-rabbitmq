package com.lwl.boot.callback;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CallBackReceiver {

	@RabbitListener(queues = "topic.callback")
	 public void process(String msg) {
	       System.out.println("CallBackReceiver  : " +msg);
    }
	
}
