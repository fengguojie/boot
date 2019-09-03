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
		Cache cache = cm.getCache("data-cache"); //根据缓存名称获取缓存
		Assert.assertNotNull(cache);
        CacheConfiguration configuration = cache.getCacheConfiguration();
		configuration.setTimeToIdleSeconds(3600);
		//缓存在内存中的配置信息，缓存配置动态修改也会体现出来,
		System.out.println(cm.getActiveConfigurationText());

		//清除所有缓存的数据，但是缓存本身仍然存在
		//  cm.clearAll();

		//从内存中删除一个缓存以及所有的数据，Cache被销毁
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
		//把数据从内存刷到DiskStore，从DiskStore刷新到Disk中
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
