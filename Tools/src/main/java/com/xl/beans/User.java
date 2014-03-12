package com.xl.beans;

import java.util.Date;

/**
 * 一个用于测试的bean
 * @author haoxiaolei
 *
 */
public class User {
	private Long id;
	private String name;
	private int age;
	private Date birthday;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		System.out.println("执行了getName()方法");
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
