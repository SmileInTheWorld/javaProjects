package com.smileintheworld.blog.mapper;

import java.util.List;

import com.smileintheworld.blog.dao.Blog;

public interface BlogMapper_forxml {
	public void insertBlog(Blog b);
	public List<Blog> querryAll();
	public Blog querryById(int id);
	public Blog updateById(Blog b);
	public void DeleteById(int id);
}
