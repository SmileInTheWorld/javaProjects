package com.smileintheworld.blog.dao;

import javax.persistence.Id;

public class Comment {
	@Id
	int id;
	String username;
	String content;
	int blog_id;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}
	@Override
	public String toString() {
		return "{id="+id+",blog_id="+blog_id+",username="+username+",content="+content+"}";
	}
}
