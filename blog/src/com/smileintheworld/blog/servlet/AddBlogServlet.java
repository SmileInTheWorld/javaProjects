package com.smileintheworld.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.smileintheworld.blog.dao.Blog;
import com.smileintheworld.blog.dao.Category;
import com.smileintheworld.blog.dao.Comment;
import com.smileintheworld.blog.mapper.BlogMapper;
import com.smileintheworld.blog.mapper.CategoryMapper;
import com.smileintheworld.blog.mapper.CommentMapper;

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
			case "to_updateblog":
				toUpdateBlogPage(request, response);
				break;
			case "insert_blog":
				addBlog(request, response);
				break;
			case "delete_blog":
				deleteBlog(request, response);
				break;
			default:
				response.sendError(400, "the value of method:  " + method + ", unsupported !");
				break;
			}
		}catch(NullPointerException e){
			response.sendError(400,"parameter (method) not found !");
		}
	}

	private void toUpdateBlogPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String blog_id_str = request.getParameter("blog_id");
		if(null != blog_id_str) {
			Category cd = new Category();
			CategoryMapper cmap = cd.openSqlSession().getMapper(CategoryMapper.class);
			Example example = new Example(Category.class);
			example.setOrderByClause("level ASC");
			List<Category> cl = cmap.selectByExample(example);
			cd.closeSqlSession();
			
			Blog bd =  new Blog();
			BlogMapper bmap = bd.openSqlSession().getMapper(BlogMapper.class);
			bd.setId(Integer.valueOf(blog_id_str));
			Blog blog = bmap.selectByPrimaryKey(bd);
			bd.closeSqlSession();
			Gson gson = new Gson();
			String ablog = gson.toJson(blog);
			HttpSession session = request.getSession();
			session.setAttribute("ablog", ablog);
			session.setAttribute("category_list", cl);
			response.sendRedirect(request.getContextPath()+"/self/updateBlog.jsp");
		}else {

		}
	}

	private void deleteBlog(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		try {
			String blog_id_str = request.getParameter("blog_id");
			Blog bd = new Blog();
			Comment comd = new Comment();
			BlogMapper bmapper = bd.openSqlSession().getMapper(BlogMapper.class);
			CommentMapper commapper = comd.openSqlSession().getMapper(CommentMapper.class);
			/* delete comment before delete blog*/
			Comment c = new Comment();
			c.setBlog_id(Integer.parseInt(blog_id_str));
			commapper.delete(c);
			int ret = bmapper.deleteByPrimaryKey(Integer.parseInt(blog_id_str));
			bd.closeSqlSession();
			System.out.println(ret);
			if(1 == ret) {
				out.print("delete success");
			}else {
				out.print("delete failed");
			}
		} catch(Exception e) {
			out.print("delete failed");
			e.printStackTrace();

		}finally {
			out.close();
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
		Integer user_id_str = (Integer)request.getSession().getAttribute("user_id");
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
