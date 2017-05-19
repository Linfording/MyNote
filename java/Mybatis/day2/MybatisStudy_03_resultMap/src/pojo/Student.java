
package pojo;

import java.util.List;

public class Student {
	private Integer id;
	private String name;
	private Integer age;
	private List<Teacher> teacherList;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public List<Teacher> getTeacherList() {
		return teacherList;
	}
	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age
				+ ", teacherList=" + teacherList + "]";
	}
}
