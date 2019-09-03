package com.jellard.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jellard.service.StudentService;

public class SpringIocContainer {
	
	public static void main(String[] args) {
		/**
		 * ClassPathXmlApplicationContext ����������ָ����������
		 * String configLocations[],  �����ļ�·�������Զ����
		 * boolean refresh, 
		 * ApplicationContext parent
		 */
		ApplicationContext context = new ClassPathXmlApplicationContext("/config/context.xml");
		StudentService studentService = (StudentService) context.getBean("studentService");
		studentService.test();
		
	}
	

}
