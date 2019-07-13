package com.smileintheworld.blog.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.mapper.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.smileintheworld.blog.dao.Blog;
import com.smileintheworld.blog.dao.Category;
import com.smileintheworld.blog.dao.Comment;
import com.smileintheworld.blog.mapper.BlogMapper;
import com.smileintheworld.blog.mapper.CategoryMapper;
import com.smileintheworld.blog.mapper.CommentMapper;

import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
/**
 * Servlet implementation class BlogServlet
 */
@WebServlet("/BlogServlet")
public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String category_id = request.getParameter("category");
		String content = request.getParameter("content");
		
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	

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
		mapperHelper.processConfiguration(sqlSessionFactory.getConfiguration());
		
		SqlSession session = sqlSessionFactory.openSession(true);
		BlogMapper bm = session.getMapper(BlogMapper.class);
		CategoryMapper cam = session.getMapper(CategoryMapper.class);
		CommentMapper com = session.getMapper(CommentMapper.class);
		try {
			List<Blog> bl = bm.select(new Blog());
			List<Category> cal = cam.select(new Category());
			List<Comment> col = com.select(new Comment());
			for(Blog b : bl ) {
				System.out.println(b);
			}
			for(Category ca : cal ) {
				System.out.println(ca);
			}
			for(Comment co : col ) {
				System.out.println(co);
			}
		} finally {
			session.close();
		}		
		
	}

}
