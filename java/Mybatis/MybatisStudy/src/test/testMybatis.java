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
//		读取核心配置文件sqlMapConfig.xml
		InputStream inputStream=Resources.getResourceAsStream("sqlMapConfig.xml");
//		获取SqlSessionFactory对象
		sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void test01() {
//		获取SqlSession
		SqlSession sqlSession = sessionFactory.openSession();
//		调用"namespace+id"
		List<User> userList = sqlSession.selectList("pojo.UserMapper.findAll");
		for (User user : userList) {
			System.out.println(user);
		}
	}
	@Test
	public void test02() {
//		获取SqlSession
		SqlSession sqlSession = sessionFactory.openSession();
		User user = sqlSession.selectOne("pojo.UserMapper.findAllById",6);
		System.out.println(user);
	}
	@Test
	public void test03() {
		SqlSession sqlSession = sessionFactory.openSession();
		
		User user=new User();
		user.setName("陆逊");
		user.setAge(23);
		user.setSex("男");
		
		int rows = sqlSession.insert("pojo.UserMapper.addUser",user);
		sqlSession.commit();
		System.out.println(rows);
	}
	@Test
	public void test04() {
		SqlSession sqlSession = sessionFactory.openSession();
		User user=new User();
		user.setId(22);
		user.setName("孙策");
		user.setAge(19);
		user.setSex("男");
		int rows = sqlSession.update("pojo.UserMapper.updateUser",user);
		sqlSession.commit();
		System.out.println(rows);
	}
//	根据年龄查找    4000>age>150
	@Test
	public void test05() {
		SqlSession sqlSession = sessionFactory.openSession();
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("minAge", 150);
		map.put("maxAge", 4000);
		
		List<User> userList = sqlSession.selectList("pojo.UserMapper.findUserByAge",map);
		for (User user : userList) {
			System.out.println(user);
		}
	}
//	根据年龄排序
	@Test
	public void test06() {
		SqlSession sqlSession = sessionFactory.openSession();
		
		Map<String, String> map=new HashMap<String, String>(); 
		map.put("column", "age");
		
		List<User> userList = sqlSession.selectList("pojo.UserMapper.findUserOrderByAge",map);
		for (User user : userList) {
			System.out.println(user);
		}
	}
//	测试动态更新
	@Test
	public void test07() {
		User user=new User();
		user.setId(21);
//		user.setName("黄盖");
//		user.setAge(189);
//		user.setSex("男");
		
		SqlSession sqlSession = sessionFactory.openSession();
		
		int rows=sqlSession.update("pojo.UserMapper.DynamicUpdateUser",user);
		sqlSession.commit();
		System.out.println(rows);
	}
	
//	测试动态查询
	@Test
	public void test08() {
		User user=new User();
//		user.setId(21);
		user.setName("黄盖");
		user.setAge(189);
		user.setSex("男");
		
		SqlSession sqlSession = sessionFactory.openSession();
		
		List<User> userList = sqlSession.selectList("pojo.UserMapper.DynamicSelect", user);
		for (User user2 : userList) {
			System.out.println(user2);
		}
	}
	
//	测试批量删除
	@Test
	public void test09() {
		Integer[] ids={1,3,5};
//		List<>
//		Map<"ids",List<>/array>
		
		SqlSession sqlSession = sessionFactory.openSession();
		
		int rows=sqlSession.delete("pojo.UserMapper.deleteUsers", ids);
		sqlSession.commit();
		System.out.println(rows);
	}
	
	@Test
	public void test10() {
//		Integer[] ids={21,28,26};
		List<Integer> ids=new ArrayList<Integer>();
		ids.add(21);
		ids.add(28);
		ids.add(26);
		Map<String, List<Integer>> map=new HashMap<String, List<Integer>>();
		map.put("ids", ids);
		SqlSession sqlSession = sessionFactory.openSession();
//		List<User> userList=sqlSession.selectList("pojo.UserMapper.selectIn", ids);
		List<User> userList=sqlSession.selectList("pojo.UserMapper.selectIn", map);
		for (User user2 : userList) {
			System.out.println(user2);
		}
	}
	
	
}
