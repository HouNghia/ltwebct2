package vn.iostar.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static String USERNAME = "root";
	private static String PASSWORD = "n@123";
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/ltwebct2"; // Tên database là "login"

	// Phương thức kết nối với cơ sở dữ liệu
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	// Phương thức main để kiểm tra kết nối và truy vấn dữ liệu
	public static void main(String[] args) {
		try {
			Connection connection = getConnection();
			if (connection == null) {
				System.out.println("Có lỗi khi kết nối với cơ sở dữ liệu");
			} else {
				System.out.println("Kết nối thành công!");

				// Thực hiện truy vấn để lấy dữ liệu từ bảng "users"
				String query = "SELECT * FROM users"; // Giả sử bạn có bảng tên là "users"
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);

				// Duyệt qua các kết quả từ truy vấn
				while (resultSet.next()) {
					// In ra dữ liệu của cột "username" từ bảng
					String username = resultSet.getString("username");
					String password = resultSet.getString("password");
					System.out.println("Username: " + username);
					System.out.println("Username: " + password);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
