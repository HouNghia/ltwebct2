package vn.iostar.models;

public class RolesModel {
	private String roleName;
	private int roleId;
	public RolesModel(String roleName, int roleId) {
		super();
		this.roleName = roleName;
		this.roleId = roleId;
	}
	public RolesModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
