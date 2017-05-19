package test;

import java.io.InputStream;
import java.util.List;

import mapper.StudentMapper;
import mapper.TeacherMapper;
import mapper.UserMapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pojo.Student;
import pojo.Teacher;
import pojo.User;

public class UserMapperTest {
	private SqlSessionFactory sessionFactory;
	@Before
	public void setUp() throws Exception {
		InputStream inputStream =Resources.getResourceAsStream("sqlMapConfig.xml");
		sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		UserMapper userMapper=sessionFactory.openSession().getMapper(UserMapper.class);
		List<User> userList = userMapper.findAll();
		for (User user : userList) {
			System.out.println(user);
		}
	}
	@Test
	public void test2() {
		TeacherMapper teacherMapper=sessionFactory.openSession().getMapper(TeacherMapper.class);
		List<Teacher> teachers = teacherMapper.findAll();
		for (Teacher teacher : teachers) {
			System.out.println(teacher);
		}
	}
	@Test
	public void test3() {
		StudentMapper studentMapper=sessionFactory.openSession().getMapper(StudentMapper.class);
		List<Student> students = studentMapper.findAll();
		for (Student student : students) {
			System.out.println(student);
		}
	}

}
