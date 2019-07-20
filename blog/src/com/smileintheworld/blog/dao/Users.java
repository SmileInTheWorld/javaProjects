package com.smileintheworld.blog.dao;

import java.sql.Timestamp;

import javax.persistence.Id;

public class Users extends BaseDao {
	@Id
	int id;
	Timestamp register_time;
	String username;
	String password;
	String phone_number;
	String email;
	
	
	public Timestamp getRegister_time() {
		return register_time;
	}
	public void setRegister_time(Timestamp register_time) {
		this.register_time = register_time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "{id: " + id + ", username: " + username + ", password: " + password
				+ ", phone_number: " + phone_number + ", email: " +email + "}"; 
	}
	
}
