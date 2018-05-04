package com.lwl.boot.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwl.boot.callback.CallBackSender;

@RestController
public class CallBackController {

	
	@Resource
	private CallBackSender callBackSender;
	
	@RequestMapping("/callback/send")
	public String send() {
		callBackSender.send();
		return "callback success";
	}
	
}
