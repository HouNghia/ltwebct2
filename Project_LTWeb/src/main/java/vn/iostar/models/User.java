package vn.iostar.models;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class User implements Serializable{
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", username=" + username + ", fullname=" + fullname
				+ ", password=" + password + ", image=" + image + ", roleid=" + roleid + ", phone=" + phone
				+ ", createDate=" + createDate + "]";
	}
	private java.sql.Date createDate;
	private int id;
	private String email;
	private String username;
	private String fullname;
	private String password;
	private String image;
	private int roleid;
	private String phone;
	public User() {
		super();
	}
	public User(int id, String email, String username, String fullname, String password, String image, int roleid,
			String phone, Date createDate) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.image = image;
		this.roleid = roleid;
		this.phone = phone;
		this.createDate = createDate;
	}
	public java.sql.Date getcreateDate() {
        return createDate;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public String getfullname() {
		return fullname;
	}
	public void setfullname(String fullname) {
		this.fullname = fullname;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	public String getimage() {
		return image;
	}
	public void setimage(String image) {
		this.image = image;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setcreateDate(java.sql.Date createDate) {
        this.createDate = createDate;
    }
	public User(String email, String username, String fullname, String password, String image, int roleid,
			String phone, Date createDate) {
		super();
		this.email = email;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.image = image;
		this.roleid = roleid;
		this.phone = phone;
		this.createDate = createDate;
	}
}
	