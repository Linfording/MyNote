package test;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import mapper.DeptMapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pojo.Dept;

public class DeptMappeTest {

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
		DeptMapper deptMapper=sessionFactory.openSession().getMapper(DeptMapper.class);
		List<Dept> deptList = deptMapper.findAll();
		for (Dept dept : deptList) {
			System.out.println(dept);
		}
	}

}
