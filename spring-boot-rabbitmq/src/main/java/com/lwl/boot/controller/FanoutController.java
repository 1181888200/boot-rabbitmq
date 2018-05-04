package com.lwl.boot.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwl.boot.fanout.FanoutSender;

@RestController
public class FanoutController {

	@Resource
	private FanoutSender fanoutSender;
	
	
    @RequestMapping("/fanout/send")
    public String send(String msg) {
    	fanoutSender.send(msg);
    	return "成功";
    }
	
    @RequestMapping("/fanout/send2")
    public String send2(String msg) {
    	fanoutSender.send2(msg);
    	return "成功2";
    }
    
}
