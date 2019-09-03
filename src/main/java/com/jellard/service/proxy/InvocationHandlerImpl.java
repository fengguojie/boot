package com.jellard.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.jellard.service.TestLog;

public class InvocationHandlerImpl implements InvocationHandler{
	
	private Object target;
	
	public Object bind(Object target){
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("i am executed");
		//Class<?>[] clazz = target.getClass().getInterfaces();
		//String methodName = method.getName();
		//Method method2 = clazz[0].getMethod(methodName, method.getParameterTypes());
		
		if (method.isAnnotationPresent(TestLog.class)) {
			System.out.println("has annotion");
			TestLog log = method.getAnnotation(TestLog.class);
			System.err.println(log.log());
			
			Object result = method.invoke(target, args);
			System.out.println("result: "+result.toString());
			return result;
		} else {

		}
		
		
		return null;
	}

}
