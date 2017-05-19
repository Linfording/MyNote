package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import pojo.User;


public class testMybatis {
	private SqlSessionFactory sessionFactory;
	@Before
	public void setUp() throws IOException{
		InputStream inputStream=Resources.getResourceAsStream("sqlMapConfig.xml");
		sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
	}
	@Test
	public void test01() {
		SqlSession session=sessionFactory.openSession();
		List<User> userList = session.selectList("pojo.UserMapper.findAll");
		for (User user : userList) {
			System.out.println(user);
		}
	}
//	根据年龄查询用户
	@Test
	public void test02() {
		SqlSession session=sessionFactory.openSession();
		List<User> userList=session.selectList("pojo.UserMapper.findUserByAge",18);
		for (User user : userList) {
			System.out.println(user);
		}
	}
//	测试一级缓存
	@Test
	public void test03() {
		SqlSession session=sessionFactory.openSession();
		List<User> userList=session.selectList("pojo.UserMapper.findAll");
		List<User> userList2=session.selectList("pojo.UserMapper.findAll");
		List<User> userList3=session.selectList("pojo.UserMapper.findAll");
		List<User> userList4=session.selectList("pojo.UserMapper.findAll");
		List<User> userList5=session.selectList("pojo.UserMapper.findAll");
		List<User> userList6=session.selectList("pojo.UserMapper.findAll");
		for (User user : userList6) {
			System.out.println(user);
		}
	}
//	测试二级缓存
	@Test
	public void test04() {
		SqlSession sessionA=sessionFactory.openSession();
		List<User> userList=sessionA.selectList("pojo.UserMapper.findAll");
		System.out.println(sessionA);
		
		SqlSession sessionB=sessionFactory.openSession();
		List<User> userList6=sessionB.selectList("pojo.UserMapper.findAll");
		System.out.println(sessionB);

	}
//	测试二级缓存
	@Test
	public void test05() {
		SqlSession sessionA=sessionFactory.openSession();
		List<User> userList=sessionA.selectList("pojo.UserMapper.findAll");
		sessionA.close();
		
		SqlSession sessionB=sessionFactory.openSession();
		List<User> userList6=sessionB.selectList("pojo.UserMapper.findAll");
		for (User user : userList6) {
			System.out.println(user);
		}
	}
	
	
}
