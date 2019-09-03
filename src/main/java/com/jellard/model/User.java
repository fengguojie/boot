package com.jellard.model;

/**
 * Created by zl on 2015/8/27.
 */
public class User {
    private String name;
    private Integer age;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String toString(){
    	StringBuffer sBuffer = new StringBuffer();
    	sBuffer.append("name"+name);
    	sBuffer.append("age"+age);
    	sBuffer.append("password"+password);
    	return sBuffer.toString();
    }
    @Override
    public boolean equals(Object obj) {
    	if (this == obj ) return true;
    	if (obj == null || obj instanceof User) return false;
    	User user = (User) obj;
    	if (user.getName().equals(name)&&user.getPassword().equals(password)&&user.getAge().equals(obj)) {
			return true;
		}
    	return false;
    }
    @Override
    public int hashCode(){
    	return name.hashCode()+password.hashCode()+age;
    }
}
