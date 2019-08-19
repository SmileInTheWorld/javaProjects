package com.smileintheworld.blog.dao;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Id;

public class Blog extends BaseDao {
	
	@Id
	Integer id;
	String title;
	String content;
	Timestamp created_time;
	Integer category_id;
	Integer user_id;
	Users user;
	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Timestamp created_time) {
		this.created_time = created_time;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", content=" + content + ", created_time=" + created_time
				+ ", category_id=" + category_id + ", user_id=" + user_id + ", user=" + user + "]";
	}
	
}
