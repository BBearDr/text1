package com.session.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:生成token传回form
 * Date:2017/9/21 13:55
 * Author:cjx
 */
public class FormServlet extends HttpServlet {
    private static final long serialVersionUID = -1159588405335338052L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = TokenProcessor.getInstance().makeToken();
        System.out.println("生成的token令牌:" + token);
        req.getSession().setAttribute("token",token);
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
