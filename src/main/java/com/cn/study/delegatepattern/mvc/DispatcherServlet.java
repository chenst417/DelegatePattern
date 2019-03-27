package com.cn.study.delegatepattern.mvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

//spring mvc
public class DispatcherServlet extends HttpServlet {

    private List<Handler> handlerMappings = new ArrayList<Handler>();

    @Override
    public void init() throws ServletException {

        Class<?> memberControllerClass = MemberController.class;

        try{
            handlerMappings.add(new Handler().setController(memberControllerClass.newInstance())
                    .setMethod(memberControllerClass.getMethod("getMemberById", new Class[]{String.class}))
                    .setUrl("/web/getMemberById.json"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //完成调度
        doDispatch(req, resp);

    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp){

        /*String uri = req.getRequestURI();
        String mid = req.getParameter("mid");

        if (uri.equals("getMemberById")) {
            new MemberController().getMemberById(mid);
        } else if (uri.equals("getOrderById")) {
            new OrderController().getOrderById(mid);
        } else if (uri.equals("logout")) {
            new SystemController().logout();
        } else {
            try{
                resp.getWriter().write(" 404 Not Found!!");
            }catch (Exception e){
                e.printStackTrace();
            }
        }*/

        String uri = req.getRequestURI();
        Handler handler = null;
        for (Handler h: handlerMappings) {
            if(h.getUrl().equals(uri)){
                handler = h;
                break;
            }
        }
        try{
            Object obj = handler.method.invoke(handler.getController(), req.getParameter("mid"));
            resp.getWriter().write(obj.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    class Handler {

        private Object controller;
        private String url;
        private Method method;

        public Object getController() {
            return controller;
        }

        public Handler setController(Object controller) {
            this.controller = controller;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Handler setUrl(String url) {
            this.url = url;
            return this;
        }

        public Method getMethod() {
            return method;
        }

        public Handler setMethod(Method method) {
            this.method = method;
            return this;
        }
    }

}
