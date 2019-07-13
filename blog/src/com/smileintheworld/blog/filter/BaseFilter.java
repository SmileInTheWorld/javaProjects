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

/**
 * Servlet Filter implementation class BaseFilter
 */
@WebFilter("/b")
public class BaseFilter implements Filter {

	private String encoding;
    /**
     * Default constructor. 
     */
    public BaseFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}
	
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		encoding = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(null == encoding ? "UTF-8" : encoding);
		//response.setCharacterEncoding(null == encoding ? "UTF-8" : encoding);
		response.setContentType("text/html;charset=" + (null == encoding ? "UTF-8" :encoding));
		System.out.println(encoding);
		chain.doFilter(request, response);
	}
}
