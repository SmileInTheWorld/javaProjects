package com.smileintheworld.blog.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

public abstract class BaseDao {
	private SqlSession  sql_session;
	private SqlSessionFactory sqlSessionFactory;

	public SqlSession openSqlSession() {
		this.initDao();
		this.sql_session = this.sqlSessionFactory.openSession(true);
		return this.sql_session;
	}
	
	public void closeSqlSession(){
		this.sql_session.close();
		this.sql_session = null;
	}
	
	private void initDao() { 
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	

		/*设置mapper*/
		MapperHelper mapperHelper = new MapperHelper();
		//特殊配置
		Config config = new Config();
		// 主键自增回写方法,默认值MYSQL,详细说明请看文档
		config.setIDENTITY("MYSQL");
		// 支持getter和setter方法上的注解
		config.setEnableMethodAnnotation(true);
		// 设置 insert 和 update 中，是否判断字符串类型!=''
		config.setNotEmpty(true);
		// 校验Example中的类型和最终调用时Mapper的泛型是否一致
		config.setCheckExampleEntityClass(true);
		// 启用简单类型
		config.setUseSimpleType(true);
		// 枚举按简单类型处理
		config.setEnumAsSimpleType(true);
		// 自动处理关键字 - mysql
		config.setWrapKeyword("`{0}`");
		//设置配置
		mapperHelper.setConfig(config);
		// 注册自己项目中使用的通用Mapper接口，这里没有默认值，必须手动注册
		mapperHelper.registerMapper(Mapper.class);
		//配置完成后，执行下面的操作
		mapperHelper.processConfiguration(this.sqlSessionFactory.getConfiguration());
	}
}
