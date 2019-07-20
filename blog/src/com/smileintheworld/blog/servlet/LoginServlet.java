package com.smileintheworld.blog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smileintheworld.blog.dao.Users;
import com.smileintheworld.blog.mapper.UsersMapper;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if(null == username  || null == password) {
			session.setAttribute("loginResult", "user name or password is null");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else {
			Users ud= new Users();
			UsersMapper umap = ud.openSqlSession().getMapper(UsersMapper.class);
			ud.setUsername(username);
			Users user = umap.selectOne(ud);
			ud.closeSqlSession(); //must close the sqlSession
			System.out.println(user);
			if(null == user) {
				session.setAttribute("loginResult", "user dosen't exist");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}else {
				if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
					session.setAttribute("user_id", user.getId());
					session.setAttribute("username", username);
					session.setAttribute("loginResult", "login success");
					response.sendRedirect(request.getContextPath()+"/self/overview.jsp");
				}else {
					session.setAttribute("loginResult", "user name or password doesn't match");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
