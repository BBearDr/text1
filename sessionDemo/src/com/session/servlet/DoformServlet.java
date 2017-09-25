package com.session.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 * Date:2017/9/21 11:24
 * Author:cjx
 */
public class DoformServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (isRequestToken(req)) {
            System.out.println("不要重复提交");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            //处理请求
            req.getSession().removeAttribute("token");
            System.out.println("处理请求");
        }
    }
    //判断token是否一致
    private boolean isRequestToken(HttpServletRequest request) {
        String token = request.getParameter("token");
        System.out.println("前台穿过的token:"+token);
        //没有token说明是重复提交
        if (token == null) {
            return true;
        }
        //取出存储的token
        String server_token = (String) request.getSession().getAttribute("token");
        System.out.println("存储的token:"+token);
        if (server_token == null) {
            return true;
        }
        //不相等也是重复提交
        if (!token.equals(server_token)) {
            return true;
        }
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
        req.getRequestDispatcher("/index2.jsp").forward(req,resp);
    }
}
