package mapper;

import java.util.List;

import pojo.User;

public interface UserMapper {
	public List<User> findAll();
	public User findUserByName(String name);
}
