package com.xiaolei.webapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SetCharacterEncodingFilter implements Filter {

	protected String encoding;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset=" + encoding);
		filterChain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
	}

}
