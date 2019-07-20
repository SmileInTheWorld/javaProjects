package com.smileintheworld.blog.dao;

import java.util.List;

import javax.persistence.Id;

import com.smileintheworld.blog.mapper.CategoryMapper;

import tk.mybatis.mapper.entity.Example;

public class Category extends BaseDao {
	@Id
	Integer id;
	String name;
	Integer level;
	
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
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return"{id="+id+",name="+name+",level="+level+"}"; 
	}
	
}
