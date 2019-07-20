package com.smileintheworld.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.smileintheworld.blog.dao.Blog;
import com.smileintheworld.blog.dao.Category;
import com.smileintheworld.blog.mapper.BlogMapper;
import com.smileintheworld.blog.mapper.CategoryMapper;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * Servlet implementation class BlogListServlet
 */
@WebServlet("/BlogList")
public class BlogListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			String method = request.getParameter("method");
			switch(method) {
			case "to_overview":
				toOverviewPage(request, response);
				break;
			case "to_showblog":
				toShowBlogPage(request, response);
				break;
			case "to_bloglist":
				toBlogListPage(request, response);
				break;
			case "show_bloglist":
				String category_id_str = request.getParameter("category_id");
				String page = request.getParameter("page");
				String limit = request.getParameter("limit");
				String blognum = request.getParameter("blognum");
				
				if(null != category_id_str && null != page 
						&& null != limit && null != blognum) {
					Blog bd = new Blog();
					BlogMapper bmap = bd.openSqlSession().getMapper(BlogMapper.class);
					Example example = new Example(Blog.class);
					Criteria criteria = example.createCriteria();
					
					example.setOrderByClause("created_time DESC");
					bmap.selectByExampleAndRowBounds(example, new RowBounds(
							Integer.parseInt(page)*Integer.parseInt(limit),
							Integer.parseInt(limit)));
					bd.closeSqlSession();
					/*json*/
					JsonObject root = new JsonObject();
					root.addProperty("code", 0);
					root.addProperty("message", "success");
					root.addProperty("count", 1000);
					JsonArray  data = new JsonArray();
					JsonObject subdata = new JsonObject();
					subdata.addProperty("title", "qqq");
					subdata.addProperty("id", 7);
					subdata.addProperty("created_time", "20190803");
					subdata.addProperty("user", "eolin");
					data.add(subdata);
					subdata = new JsonObject();
					subdata.addProperty("title", "123");
					subdata.addProperty("id", 4);
					subdata.addProperty("created_time", "20190804");
					subdata.addProperty("user", "admin");
					data.add(subdata);
					subdata = new JsonObject();
					root.add("data", data);
					Gson g = new Gson();
					String jstr = g.toJson(root);
					System.out.println(jstr);
					response.setContentType("contentType:application/json charset:UTf-8");
					PrintWriter out = response.getWriter();
					out.print(jstr);
					out.close();
				}
				break;
			default:
				response.sendError(400, "the value of method:  " + method + ", unsupported !");
				break;
			}
		}catch(NullPointerException e){
			response.sendError(400,"parameter (method) not found !");
		}
	}

	private void toBlogListPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Category cd = new Category();
		CategoryMapper cmap = cd.openSqlSession().getMapper(CategoryMapper.class);
		Example example = new Example(Category.class);
		example.setOrderByClause("level ASC");
		List<Category> cl = cmap.selectByExample(example);
		cd.closeSqlSession();
		List<Integer> blognum = new LinkedList<Integer>();
		Blog bd = new Blog();
		BlogMapper bmap = bd.openSqlSession().getMapper(BlogMapper.class);
		for(Category e : cl) {
			bd.setCategory_id(e.getId());
			blognum.add(bmap.selectCount(bd));
		}
		bd.closeSqlSession();
		request.getSession().setAttribute("category_list", cl);
		request.getSession().setAttribute("blognum",blognum);
		response.sendRedirect(request.getContextPath()+"/self/blogList.jsp");
	}

	private void toOverviewPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Blog bd =  new Blog();
		BlogMapper bmap = bd.openSqlSession().getMapper(BlogMapper.class);
		Example	example = new Example(Blog.class);
		example.setOrderByClause("created_time DESC");
		List<Blog> bl = bmap.selectByExampleAndRowBounds(example, new RowBounds(0,4));
		bd.closeSqlSession();
		String content = null;
		for(Blog e : bl) {
			System.out.println(e);
			content = e.getContent();
			if(null != content && 200< content.length()) {
				e.setContent(content.substring(0, 200) + "...");//有bug计算了副文本的html标签的长度
			}
		}
		request.getSession().setAttribute("latest_blogs", bl);
		response.sendRedirect(request.getContextPath()+"/self/overview.jsp");
	}

	private void toShowBlogPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id_str = request.getParameter("blog_id");
		if(null != id_str) {
			Blog bd =  new Blog();
			BlogMapper bmap = bd.openSqlSession().getMapper(BlogMapper.class);
			bd.setId(Integer.valueOf(id_str));
			Blog blog = bmap.selectByPrimaryKey(bd);
			bd.closeSqlSession();
			System.out.println(blog);
			request.getSession().setAttribute("ablog", blog);
			response.sendRedirect(request.getContextPath()+"/self/showBlog.jsp");
		}else {
			response.sendError(400,"parameter (id) not found !");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
