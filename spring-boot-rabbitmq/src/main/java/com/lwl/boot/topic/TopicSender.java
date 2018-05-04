package com.lwl.boot.topic;

import org.springframework.stereotype.Component;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msg1 = "I am topic.mesaage msg======";
        System.out.println("sender1 : " + msg1);
        //方法的第一个参数是交换机名称,第二个参数是发送的key,第三个参数是内容
        //RabbitMQ将会根据第二个参数去寻找有没有匹配此规则的队列,如果有,则把消息给它,如果有不止一个,则把消息分发给匹配的队列(每个队列都有消息!)
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", msg1);
        
        String msg2 = "I am topic.mesaages msg########";
        System.out.println("sender2 : " + msg2);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", msg2);
    }

}