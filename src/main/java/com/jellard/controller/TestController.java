package com.jellard.controller;

import com.jellard.service.StudentService;
import com.jellard.service.TeacherService;
import com.jellard.utils.CustomIocContainer;

public class TestController {
	
	private StudentService studentService;
	private TeacherService teacherService;
	
	public StudentService getStudentService() {
		return studentService;
	}
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	public TeacherService getTeacherService() {
		return teacherService;
	}
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	public static void main(String[] args) {
		CustomIocContainer iocContainer = new CustomIocContainer();
		try {
			StudentService ss = (StudentService) iocContainer.getBean("studentService");
			TeacherService tt = (TeacherService) iocContainer.getBean("teacherService");
			TestController testController = (TestController) iocContainer.getBean("testController");
			testController.getStudentService().test();
			testController.getTeacherService().test();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	

}
