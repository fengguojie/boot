package com.jellard.service.impl;

import com.jellard.service.TeacherService;

public class TeacherServiceImpl implements TeacherService{

	public void test() {
		System.out.println("test teacher module!");
		
	}

	public String say(String content) {
		System.out.println(content);
		return content;
	}

}
