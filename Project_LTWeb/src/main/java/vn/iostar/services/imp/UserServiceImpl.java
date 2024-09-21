package vn.iostar.services.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.iostar.configs.DBConnection;
import vn.iostar.dao.UserDao;
import vn.iostar.dao.imp.UserDaoImpl;
import vn.iostar.services.UserService;
import vn.iostar.models.User;

public class UserServiceImpl implements UserService{
	UserDao userDao = new UserDaoImpl(); 
	@Override
	public void insert(User user) {
	    String sql = "INSERT INTO users (username, password, image, fullname, email, roleid, phone, createDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; 
	    // Chú ý "createDate" đúng với tên cột trong cơ sở dữ liệu của bạn
	    
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        // Thiết lập các tham số cho PreparedStatement
	        ps.setString(1, user.getusername());
	        ps.setString(2, user.getpassword());
	        ps.setString(3, user.getimage()); 
	        ps.setString(4, user.getfullname());
	        ps.setString(5, user.getEmail());
	        ps.setInt(6, user.getRoleid());
	        ps.setString(7, user.getPhone());
	        ps.setTimestamp(8, new java.sql.Timestamp(user.getcreateDate().getTime()));

	        // Thực hiện lệnh insert
	        ps.executeUpdate();
	        System.out.println("User saved successfully!");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}




	public User findByUserName(String username) {
	    User user = null;
	    String sql = "SELECT * FROM users WHERE username = ?"; // Giả sử bảng của bạn có cột tên là 'username'
	    
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, username);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            user = new User();
	            user.setusername(rs.getString("username"));
	            user.setpassword(rs.getString("password"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return user; 
	}


	@Override
	public boolean register(String email, String password, String username, String fullname, String phone, java.sql.Date createDate) {
	    // Kiểm tra xem tài khoản hoặc email đã tồn tại hay chưa
	    if (userDao.checkExistUsername(username) || userDao.checkExistEmail(email)) {
	        return false; // Trả về false nếu tài khoản hoặc email đã tồn tại
	    }

	    // Tạo đối tượng User mới
	    User newUser = new User(email, username, fullname, password, null, 5, phone, createDate); 
	    // image để null, roleid là 5 cho người dùng thông thường

	    // Thực hiện lưu người dùng mới vào cơ sở dữ liệu
	    userDao.insert(newUser); // Thực hiện lưu mà không cần giá trị trả về
	    return true; // Trả về true nếu không có lỗi
	}



	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}
	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}
	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}

	@Override
	public User login(String username, String password) {
	    // Tìm kiếm người dùng dựa trên username
	    User user = this.findByUserName(username);

	    if (user != null) {
	        // In ra console thông tin người dùng và mật khẩu để so sánh
	        System.out.println("Tìm thấy người dùng: " + user.getusername());
	        System.out.println("Mật khẩu trong cơ sở dữ liệu: " + user.getpassword());
	        System.out.println("Mật khẩu người dùng nhập vào: " + password);

	        // Kiểm tra xem mật khẩu có khớp không
	        if (password.equals(user.getpassword())) { // So sánh trực tiếp nếu không có mã hóa
	            System.out.println("Mật khẩu khớp. Đăng nhập thành công.");
	            return user; // Đăng nhập thành công
	        } else {
	            System.out.println("Mật khẩu không khớp.");
	            return null; // Mật khẩu sai
	        }
	    } else {
	        System.out.println("Tên đăng nhập không tồn tại: " + username);
	        return null; // Người dùng không tồn tại
	    }
	}


	@Override
	public User get(int id) {
		// TODO 
		return userDao.get(id);
	}
	
	
	@Override
	public boolean changePassword(String username, String newPassword) {
		return userDao.changePassword(username, newPassword);
	}
	
}