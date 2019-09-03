package com.jellard.service.impl;

import com.jellard.service.StudentService;

public class StudentServiceImpl implements StudentService{
	
	public void test() {
		System.out.println("test student module!");
		
	}
    public String say(String who, String name) {
		System.out.println(who+":"+name);
		return who+":"+name;
	}

}
