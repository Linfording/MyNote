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
		 * ˵��:һ��ҵ������Ĵ��룬��Ӧ��д��Service���У���֤�����һ����
		 * ����������д��servlet����޷���֤����A������Bͬʱ�ύ���ع�
		 */
		userService.addUser(user);//����A
		personService.addPerson();//����B
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
