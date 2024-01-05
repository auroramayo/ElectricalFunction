package org.user;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandleInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handle) throws Exception{
        Object user = request.getSession().getAttribute("sessionID");
        if(user == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
