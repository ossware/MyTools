package com.ehome.springmvc.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package com.ehome.springmvc.filter
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 2014/5/24 0024 23:16
 * @Copyright: 2014 ihome.com
 */
public class CharacterEncodingFilter implements Filter {

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