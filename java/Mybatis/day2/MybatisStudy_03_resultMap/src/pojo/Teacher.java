package pojo;

import java.util.List;

public class Teacher {
	private Integer id;
	private String name;
	private String sex ;
	private List<Student> studentList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", studentList=" + studentList + "]";
	}
}
