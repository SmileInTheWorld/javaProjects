package com.smileintheworld.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smileintheworld.blog.dao.Blog;

import tk.mybatis.mapper.common.Mapper;

public interface BlogMapper extends Mapper<Blog> {
	public List<Blog> selectBlogListByCategoryId(@Param("category_id") int category_id,
												@Param("offset") int offset, 
												@Param("limit") int limit);
}
