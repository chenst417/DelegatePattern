package com.cn.study.delegatepattern.mvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//spring mvc
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //完成调度
        doDispatch(req, resp);

    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp){

        String uri = req.getRequestURI();
        String mid = req.getParameter("mid");

        if (uri.equals("getMemberById")) {
            new MemberController().getMemberById(mid);
        } else if (uri.equals("getOrderById")) {
            new OrderController().getOrderById(mid);
        } else if (uri.equals("logout")) {
            new SystemController().logout();
        }

    }
}
