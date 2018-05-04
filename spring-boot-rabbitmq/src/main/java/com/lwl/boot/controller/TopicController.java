package com.lwl.boot.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwl.boot.topic.TopicSender;

@RestController
public class TopicController {

	@Resource
	private TopicSender topicSend;
	
	 @RequestMapping("/topic/send")
    public String topicTest() {
		 topicSend.send();
		 return "成功";
    }
	
}
