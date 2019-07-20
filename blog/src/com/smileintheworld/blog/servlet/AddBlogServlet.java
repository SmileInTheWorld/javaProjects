package com.smileintheworld.blog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.smileintheworld.blog.dao.Blog;
import com.smileintheworld.blog.dao.Category;
import com.smileintheworld.blog.mapper.BlogMapper;
import com.smileintheworld.blog.mapper.CategoryMapper;

import tk.mybatis.mapper.entity.Example;
/**
 * Servlet implementation class BlogServlet
 */
@WebServlet("/AddBlog")
public class AddBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddBlogServlet() {
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
		try {
			String method = request.getParameter("method");
			switch(method) {
			case "to_addblog":
				toAddBlogPage(request, response);
				break;
			case "insert":
				addBlog(request, response);
				break;
			default:
				response.sendError(400, "the value of method:  " + method + ", unsupported !");
				break;
			}
		}catch(NullPointerException e){
			response.sendError(400,"parameter (method) not found !");
		}
	}

	private void toAddBlogPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Category cd = new Category();
		CategoryMapper cmap = cd.openSqlSession().getMapper(CategoryMapper.class);
		Example example = new Example(Category.class);
		example.setOrderByClause("level ASC");
		List<Category> cl = cmap.selectByExample(example);
		cd.closeSqlSession();
		request.getSession().setAttribute("category_list", cl);
		response.sendRedirect(request.getContextPath()+"/self/addBlog.jsp");
	}

	private void addBlog(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String title = request.getParameter("title");
		String category_id_str = request.getParameter("category");
		String content = request.getParameter("content");
		String user_id_str = (String)request.getSession().getAttribute("user_id");
		System.out.println(title);
		System.out.println(category_id_str);
		System.out.println(content);
		System.out.println(user_id_str);
		if(null != title && null != category_id_str && null != content) {
			if(0 < title.length() && 0 < category_id_str.length() && 0 < content.length()) {
				if(null == user_id_str) {
					response.sendError(403, "invalid user!");
				}else {
					Blog bd = new Blog();
					bd.setUser_id(Integer.valueOf(user_id_str));
					bd.setTitle(title);
					bd.setCategory_id(Integer.valueOf(category_id_str));
					bd.setContent(content);
					java.sql.Timestamp  t= new java.sql.Timestamp(new java.util.Date().getTime());
					bd.setCreated_time(t);
					BlogMapper bmap = bd.openSqlSession().getMapper(BlogMapper.class);
					bmap.insert(bd);
					bd.closeSqlSession();
				}
			}else {
				response.sendError(400, "invalid parameters !");
			}
		}else {
				response.sendError(400,"some parameters not found !");
		}
	}
}
