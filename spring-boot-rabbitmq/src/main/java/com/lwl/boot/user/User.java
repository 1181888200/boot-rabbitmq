package com.lwl.boot.user;

import java.io.Serializable;

/**
 * 实体类（必须实现序列化接口）
 * @author lwlong
 *
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int age;
	
	private String name;
	
	private String sex;

	public int getAge() {
		return age;
	}

	public User setAge(int age) {
		this.age = age;
		return this;
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getSex() {
		return sex;
	}

	public User setSex(String sex) {
		this.sex = sex;
		return this;
	}

	@Override
	public String toString() {
		return "User [age=" + age + ", name=" + name + ", sex=" + sex + "]";
	}
	
	
}
