package service;

import java.sql.SQLException;
import java.util.List;

import bean.User;

public interface UserService {
	void addUser(User user) throws SQLException;
	void updateUser(User user);
	void deleteUser(Integer id);
	List<User> findAll();
	User findUserById(Integer id);
}
