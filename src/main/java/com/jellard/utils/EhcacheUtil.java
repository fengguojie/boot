package com.jellard.utils;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.jellard.model.User;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

public class EhcacheUtil {
	
	public static CacheManager getCacheManager() {
		try {
			InputStream in = EhcacheUtil.class.getResourceAsStream("/config/ehcache.xml"); 
			return CacheManager.create(in);
		} catch (CacheException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Cache getCache(CacheManager cm) {
		Cache cache = cm.getCache("data-cache"); //���ݻ������ƻ�ȡ����
		Assert.assertNotNull(cache);
        CacheConfiguration configuration = cache.getCacheConfiguration();
		configuration.setTimeToIdleSeconds(3600);
		//�������ڴ��е�������Ϣ���������ö�̬�޸�Ҳ�����ֳ���,
		System.out.println(cm.getActiveConfigurationText());

		//������л�������ݣ����ǻ��汾����Ȼ����
		//  cm.clearAll();

		//���ڴ���ɾ��һ�������Լ����е����ݣ�Cache������
		//  cm.removeCache("data-cache");
		return cache;
	}
	public static void addCache (Cache cache) {
		User user = new User();
		user.setName("aa");
		user.setPassword("123");
		user.setAge(16);
		cache.put(new Element("u1", user));
		System.out.println(cache.get("u1"));
		
		/*Element element = cache.get("u1");
		
		User user2 = (User) element.getObjectValue();
		System.out.println(user2.getName());*/
		//�����ݴ��ڴ�ˢ��DiskStore����DiskStoreˢ�µ�Disk��
		//cache.flush();
	}
	@Test
	public void testCache() {
		try {
			CacheManager cm = EhcacheUtil.getCacheManager();
			Cache cache = cm.getCache("data-cache");
			
			addCache(cache);
			Element element = cache.get("u1");
			User user2 = (User) element.getObjectValue();
			System.out.println(user2.getName());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
