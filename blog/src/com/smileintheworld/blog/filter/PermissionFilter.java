package com.smileintheworld.blog.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class PermissionFilter
 */
@WebFilter("/a")
public class PermissionFilter implements Filter {

	/**
	 * Default constructor. 
	 */
	public PermissionFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;

		HttpSession session =  httpReq.getSession();
		String servletPath = httpReq.getServletPath();
		String servletName = httpReq.getServerName();
		System.out.println("servletPath: "+ servletPath +"\nservletName: "+ servletName);

		if(null != servletPath && servletPath.equals("/index.jsp")) {
			chain.doFilter(httpReq, httpRes);
		}else {
			String loginFlag = (String)session.getAttribute("loginResult");
			if(null != loginFlag) {
				if(loginFlag.equals("login success")) {
					chain.doFilter(httpReq, httpRes);
				}else {
					httpReq.getRequestDispatcher("/index.jsp").forward(httpReq, httpRes);
				}
			}else {
				httpReq.setAttribute("loginResult", "not login");
				httpReq.getRequestDispatcher("/index.jsp").forward(httpReq, httpRes);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
