package pojo;

import java.util.List;

public class Dept {
	private Integer deptId;
	private String deptName;
	private List<User> users;
	
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "Dept [deptId=" + deptId + ", deptName=" + deptName + ", users="
				+ users + "]";
	}
	public List<User> getUser() {
		return users;
	}
	public void setUser(List<User> users) {
		this.users = users;
	}
}
