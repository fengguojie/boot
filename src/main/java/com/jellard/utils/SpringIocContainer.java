package com.jellard.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jellard.service.StudentService;

public class SpringIocContainer {
	
	public static void main(String[] args) {
		/**
		 * ClassPathXmlApplicationContext 构造器可以指定三个参数
		 * String configLocations[],  配置文件路径（可以多个）
		 * boolean refresh, 
		 * ApplicationContext parent
		 */
		ApplicationContext context = new ClassPathXmlApplicationContext("/config/context.xml");
		StudentService studentService = (StudentService) context.getBean("studentService");
		studentService.test();
		
	}
	

}
