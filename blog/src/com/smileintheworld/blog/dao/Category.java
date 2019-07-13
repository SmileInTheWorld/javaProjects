package com.smileintheworld.blog.dao;

import javax.persistence.Id;

public class Category {
	@Id
	int id;
	
	String name;
	int level;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return"{id="+id+",name="+name+",level="+level+"}"; 
	}
	
}
