package bean;

import java.util.Date;

//表示User的扩展信息
public class UserInfo {
	private Integer age;
	private Date birthday;
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserInfo [age=" + age + ", birthday=" + birthday + "]";
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
