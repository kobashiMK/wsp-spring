package com.example.wsp.spring;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Component
@Order(1)
public class SignBarrierFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws IOException, ServletException{
        var httpServletRequest = (HttpServletRequest)servletRequest;
        var path = httpServletRequest.getServletPath();
        var method = httpServletRequest.getMethod();

        System.out.println("パス:" + path + ",HTTP命令:" + method);

        if(httpServletRequest.getServletPath().equals("/GetPost") || httpServletRequest.getServletPath().equals("/test")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        if (httpServletRequest.getServletPath().equals("/Signed")&&httpServletRequest.getMethod().equals("POST")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        var userId = httpSession.getAttribute("userId");
        System.out.println(httpSession.getId() + "のuserId:"+ userId);

        if (Objects.isNull(userId)){
            var dispatcher = servletRequest.getRequestDispatcher("SignIn");
            dispatcher.forward(servletRequest,servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
    @Autowired
    private HttpSession httpSession;

}
