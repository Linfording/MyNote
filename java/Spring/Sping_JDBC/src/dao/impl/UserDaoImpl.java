package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import bean.User;
import dao.UserDao;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private RowMapper<User> userMapper;
	
	@Override
	public void addUser(User user) {
		String sql="insert into user(id,name,password) values (?,?,?)";
		jdbcTemplate.update(sql, user.getId(),user.getName(),user.getPassword());

	}

	@Override
	public void updateUser(User user) {
		String sql="update user set name=?,password=? where id=?";
		jdbcTemplate.update(sql, user.getName(),user.getPassword(),user.getId());
	}

	@Override
	public void deleteUser(Integer id) {
		String sql="delete from user where id=?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public List<User> findAll() {
		String sql="select * from user";
		return jdbcTemplate.query(sql, userMapper);
	}

	@Override
	public User findUserById(Integer id) {
		String sql="select * from user where id=?";
		return (User) jdbcTemplate.queryForObject(sql, userMapper, id);
	}
	
}
