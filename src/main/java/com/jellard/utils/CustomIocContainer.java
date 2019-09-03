package com.jellard.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CustomIocContainer {
	private static final String propertyPath= "/config/ioc.properties";
	private Properties properties = new Properties();
	private Map<String, Object> beans = new HashMap<String, Object>();
	{
		try {
			properties.load(CustomIocContainer.class.getResourceAsStream(propertyPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Object getBean(String beanName) throws ClassNotFoundException{
		Object instance;
		if (beans.containsKey(beanName)) {
			return beans.get(beanName);
		}
		String fullName = properties.getProperty(beanName);
		if (fullName == null) {
			throw new ClassNotFoundException();
		}
		Class<? extends Object> clazz = Class.forName(fullName);
		try {
			instance = clazz.newInstance();
			instance = buildAttachedBean(instance,beanName);
			beans.put(beanName, instance);
			return instance;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	private Object buildAttachedBean(Object instance, String fullName) throws ClassNotFoundException {
		Set<String> beansName = properties.stringPropertyNames();
		Field[] fields = instance.getClass().getDeclaredFields();
		for (String beanName : beansName) {
			if (beanName.contains(fullName) && !beanName.equals(fullName)) {
				Class<? extends Object> clazz = Class.forName(properties.getProperty(properties.getProperty(beanName)));
				for (Field field : fields) {
					if (field.getType().isAssignableFrom(clazz)) {
						try {
							field.setAccessible(true);
							field.set(instance, clazz.newInstance());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			
		}
		return instance;
	}

}
