package dao;

import java.sql.SQLException;
import java.util.List;

import bean.User;

public interface UserDao {
	void addUser(User user);
	void updateUser(User user);
	void deleteUser(Integer id);
	List<User> findAll();
	User findUserById(Integer id);
}
