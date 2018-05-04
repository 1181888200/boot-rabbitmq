package com.lwl.boot.controller;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwl.boot.simple.SimpleHelloSender;
import com.lwl.boot.simple.SimpleHelloSender2;


@RestController
public class SimpleHelloController {

	@Resource
	private SimpleHelloSender simpleHelloSender;
	
	@Resource
	private SimpleHelloSender2 simpleHelloSender2;
	
	@RequestMapping("/simple/send")
	public String send(String msg) {
		 simpleHelloSender.send(msg);
		return "推送成功";
	}
	
	 /**
     * 单生产者-多消费者
     * 	交替处理消息:轮询分发（round-robin）
     */
	@RequestMapping("/simple/oneToMany")
    public String oneToMany() {
        for(int i=0;i<10;i++){
        	simpleHelloSender.send("hellomsg:"+i);
        }
        return "单生产者-多消费者";
    }
	
	 /**
     * 多生产者-多消费者
     * 交替处理消息
     */
	@RequestMapping("/simple/manyToMany")
    public String manyToMany() {
        for(int i=0;i<10;i++){
        	simpleHelloSender.send("hellomsg-1-:"+i);
        	simpleHelloSender2.send("hellomsg-2-:"+i);
        }
        return "多生产者-多消费者";  
    }
}
