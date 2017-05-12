package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import servlet.UserServlet;

import bean.User;
import bean.UserMapper;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class SpringJDBCTest {

	@Test
	public void test01() throws SQLException{
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ComboPooledDataSource dataSource=(ComboPooledDataSource) context.getBean("dataSource");
		Connection conn=dataSource.getConnection();
		PreparedStatement pstat=conn.prepareStatement("select * from user");
		ResultSet rs=pstat.executeQuery();
		ResultSetMetaData rsmd=rs.getMetaData();
		while (rs.next()) {
			String len="";
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				len+=rs.getString(i+1)+",";
			}
			System.out.println(len);
		}
	}
//	增
	@Test
	public void test02() throws SQLException{
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate=(JdbcTemplate) context.getBean("jdbcTemplate");
		String insertSql="insert into user(id,name,password) values(null,?,?)";
		jdbcTemplate.update(insertSql, "xiaofeifei","asd");
		jdbcTemplate.update(insertSql, "一指禅","asd");
	}
//	改
	@Test
	public void test03() throws SQLException{
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate=(JdbcTemplate) context.getBean("jdbcTemplate");
		String updateSql="update user set name=? where id=?";
		jdbcTemplate.update(updateSql, "feifeixiao","7");
		
	}
//	删
	@Test
	public void test04() throws SQLException{
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate=(JdbcTemplate) context.getBean("jdbcTemplate");
		String updateSql="delete from user where id=?";
		jdbcTemplate.update(updateSql, "10");
		
	}
//	查1
	@Test
	public void test05() throws SQLException{
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate=(JdbcTemplate) context.getBean("jdbcTemplate");
		String querySql="select * from user ";
		List<Map<String, Object>> userList=jdbcTemplate.queryForList(querySql);
		for (Map<String, Object> map : userList) {
			System.out.println(map);
		}
		
	}
//	查2-List
	@Test
	public void test06() throws SQLException{
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate=(JdbcTemplate) context.getBean("jdbcTemplate");
		String querySql="select * from user ";
		List<User> userList=jdbcTemplate.query(querySql, new UserMapper());
		System.out.println(userList);
	}

//	查2-user
	@Test
	public void test07() throws SQLException{
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate=(JdbcTemplate) context.getBean("jdbcTemplate");
		String querySql="select * from user where id=?";
		User user=jdbcTemplate.queryForObject(querySql,new UserMapper(),8);
		System.out.println(user);
	}
//	查3-List
	@Test
	public void test08() throws SQLException{
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate=(JdbcTemplate) context.getBean("jdbcTemplate");
		String querySql="select * from user";
		List<User> userList=jdbcTemplate.query(querySql, new BeanPropertyRowMapper(User.class));
		System.out.println(userList);
	}
//	查3-user
	@Test
	public void test09() throws SQLException{
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate jdbcTemplate=(JdbcTemplate) context.getBean("jdbcTemplate");
		String querySql="select * from user where id=?";
		User user=jdbcTemplate.queryForObject(querySql,new BeanPropertyRowMapper<User>(User.class),8);
		System.out.println(user);
	}
}
