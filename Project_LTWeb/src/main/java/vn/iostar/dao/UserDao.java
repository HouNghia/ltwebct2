package vn.iostar.dao;

import java.util.List;

import vn.iostar.models.User;

public interface UserDao {
	User get(int id);
	void insert(User users);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	User findByUserName(String username);
	public List<User> findAll();
	boolean changePassword(String username, String newPassword);
}
