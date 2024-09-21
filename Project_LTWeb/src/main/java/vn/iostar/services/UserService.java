package vn.iostar.services;

import vn.iostar.models.User;

public interface UserService {
	User login(String username, String password);
	User get(int id);
	void insert(User user);
	User findByUserName(String username);
	boolean register(String email, String password, String username, String fullname, String phone, java.sql.Date createDate);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	boolean changePassword(String username, String newpassword);
}