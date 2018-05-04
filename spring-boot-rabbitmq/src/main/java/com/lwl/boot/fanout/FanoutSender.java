package com.lwl.boot.fanout;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msgString) {
        System.out.println("FanoutSender send : "+msgString);
        //第二个参数将被忽略掉
        this.rabbitTemplate.convertAndSend("fanoutExchange","", msgString);
    }
    public void send2(String msgString) {
    	System.out.println("FanoutSender2 send : "+msgString);
    	//第二个参数将被忽略掉
    	this.rabbitTemplate.convertAndSend("fanoutExchange2","", msgString);
    }

}