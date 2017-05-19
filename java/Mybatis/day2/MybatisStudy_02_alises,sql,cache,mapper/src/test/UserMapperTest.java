package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import mapper.UserMapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pojo.User;

public class UserMapperTest {

	private SqlSessionFactory sessionFactory;
	@Before
	public void setUp() throws IOException{
		InputStream inputStream=Resources.getResourceAsStream("sqlMapConfig.xml");
		sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
	}
//测试接口形式FindAll
	@Test
	public void testFindAll() {
		SqlSession session=sessionFactory.openSession();
		UserMapper userMapper=session.getMapper(UserMapper.class);
		System.out.println(userMapper);
		List<User> userList = userMapper.findAll();
		for (User user : userList) {
			System.out.println(user);
		}
	}
//测试接口形式
	@Test
	public void testFindUserByName() {
		SqlSession session=sessionFactory.openSession();
		UserMapper userMapper=session.getMapper(UserMapper.class);
		System.out.println(userMapper);
		User user=userMapper.findUserByName("陆逊");
		System.out.println(user);
	}

}
