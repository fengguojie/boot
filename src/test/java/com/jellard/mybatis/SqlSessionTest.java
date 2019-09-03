package com.jellard.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.jellard.mapper.UserMapper;
import com.jellard.model.User;

public class SqlSessionTest {
	
	private static final String RESOURCENAME = "mybatis/mybatis.xml"; 
	
	@Test
	public void test() {
		try {
			InputStream iStream = Resources.getResourceAsStream("mybatis/mybatis.xml");
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(iStream);
			SqlSession session = sessionFactory.openSession();
			//User user = session.selectOne("com.jellard.mapper.UserMapper.findUserInfo");
			//方式二  调用接口
			UserMapper mapper = (UserMapper) session.getMapper(UserMapper.class);
			User user = mapper.findUserInfo(1);
			System.out.println(user.getName());
			System.out.println(user.getPassword());
			List<User> users = mapper.findAll();
			for (User user2 : users) {
				System.out.println(user2);
			}
			System.out.println(users.size());
			session.close();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void sourceTest(){
		try {
			InputStream inputStream = Resources.getResourceAsStream(RESOURCENAME);
			XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(inputStream,null,null);
			Configuration configuration = xmlConfigBuilder.parse();
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			UserMapper mapper = (UserMapper) sqlSession.getMapper(UserMapper.class);
			List<User> users = mapper.findAll();
			for (User user : users) {
				System.out.println(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
