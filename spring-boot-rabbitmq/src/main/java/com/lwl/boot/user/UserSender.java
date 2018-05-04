package com.lwl.boot.user;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(  User user) {
        System.out.println("user send : " + user.toString());
        this.rabbitTemplate.convertAndSend("userQueue", user);
    }

}