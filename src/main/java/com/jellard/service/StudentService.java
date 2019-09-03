package com.jellard.service;

public interface StudentService {
	
	@TestLog(log = "on")
	void test();
	
	@TestLog(log = "off")
	String say(String who,String name);

}
