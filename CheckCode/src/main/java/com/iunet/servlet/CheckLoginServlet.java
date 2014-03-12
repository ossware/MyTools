package com.iunet.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: haoxiaolei
 * Date: 13-6-18
 * Time: 下午4:56
 * To change this template use File | Settings | File Templates.
 */
public class CheckLoginServlet extends HttpServlet {

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String validatecode1 = (String) request.getSession().getAttribute("ValidateCode");
        String validatecode2 = request.getParameter("validatecode");
        String message = "";
        if (!validatecode1.equals(validatecode2)) {
            message = "验证错误";
        } else {
            message = "验证正确";
        }
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(message);
//        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
