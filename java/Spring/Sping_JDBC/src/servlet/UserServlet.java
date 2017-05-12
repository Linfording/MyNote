package servlet;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import service.PersonService;
import service.UserService;

import bean.User;

@Controller
public class UserServlet {
	@Autowired
	UserService userService;
	@Autowired
	PersonService personService;
	public void addUser(User user) throws SQLException{
		/**
		 * 说明:一切业务操作的代码，都应该写在Service层中，保证事务的一致性
		 * 如果将下面的写在servlet里，将无法保证事务A和事务B同时提交、回滚
		 */
		userService.addUser(user);//事务A
		personService.addPerson();//事务B
	};
	public void updateUser(User user) {
		userService.updateUser(user);
	}
	public void deleteUser(Integer id) {
		userService.deleteUser(id);
	}
	public List<User> findAll() {
		return userService.findAll();
	}
	public User findUserById(Integer id) {
		return userService.findUserById(id);
	}
}
