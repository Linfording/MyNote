package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import servlet.UserServlet;

import bean.User;

public class UserServletTest {
	ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
	UserServlet userServlet=(UserServlet) context.getBean("userServlet");
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddUser() throws SQLException {
		User user =new User();
		user.setId(10);
		user.setName("addTest");
		user.setPassword("ppp");
		userServlet.addUser(user);
	}
	

	@Test
	public void testUpdateUser() {
		User user =new User();
		user.setId(9);
		user.setName("updateTest");
		user.setPassword("ppp");
		userServlet.updateUser(user);
	}

	@Test
	public void testDeleteUser() {
		userServlet.deleteUser(10);
	}

	@Test
	public void testFindAll() {
		List<User> list=userServlet.findAll();
		for (User user : list) {
			System.out.println(user);
		}
	}

	@Test
	public void testFindUserById() {
		User user=userServlet.findUserById(8);
		System.out.println(user);
	}

}
