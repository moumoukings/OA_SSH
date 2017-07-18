package com.jboa.action.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Shark
 *
 */
public class CommonLogin implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if (null != ((HttpServletRequest)request).getSession().getAttribute("SYS_USER")){
			chain.doFilter(request, response);
		}else{
			HttpServletResponse hsr = (HttpServletResponse) response;
			hsr.sendRedirect("/jboa2.0/login.html");
		}
	}
	@Override
	public void destroy() {	}
}
