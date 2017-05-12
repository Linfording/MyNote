package dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dao.PersonDao;
@Repository
public class PersonDaoImpl implements PersonDao{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void addPerson() {
		String sql="insert into person (id,name) values (null,'zhangsan')";
		jdbcTemplate.update(sql);
	}

}
