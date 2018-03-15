package com.imooc.dto;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.vailddator.MyConstraint;

public class User {
	
	
	public interface UserSimpleView{};
	public interface UserDetailView extends  UserSimpleView{};
	
	@JsonView(UserSimpleView.class)
	@MyConstraint(message="这是一个测试")
	private String username;
	@JsonView(UserDetailView.class)
	@NotBlank(message="密码不能为空")
	private String password;
	@JsonView(UserSimpleView.class)
	private int id;
	@JsonView(UserSimpleView.class)
	@Past(message="生日必须是过去时间")
	public Date birthday;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
