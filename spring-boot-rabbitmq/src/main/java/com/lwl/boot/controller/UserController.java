package com.lwl.boot.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwl.boot.user.User;
import com.lwl.boot.user.UserSender;

@RestController
public class UserController {

	@Resource
	private UserSender userSender;
	
	@RequestMapping("/user/send")
	public User send(int age,String name,String sex) {
		User u = new User();
		u.setAge(age).setName(name).setSex(sex);
		userSender.send(u);
		return u;
	}
	
	
}
