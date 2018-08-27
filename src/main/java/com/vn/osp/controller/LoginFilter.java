package com.vn.osp.controller;

import com.vn.osp.context.CommonContext;
import com.vn.osp.modelview.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            String contextPath = ((HttpServletRequest) request).getContextPath();
            if (checkLogined(req)) {
                chain.doFilter(request, response);
                return;
            } else {

                if (req.getRequestURI().equals(contextPath + "/")
                        || req.getRequestURI().contains(".css")
                        || req.getRequestURI().contains(".js")
                        || req.getRequestURI().contains(".jpg")
                        || req.getRequestURI().contains(".png")
                        || req.getRequestURI().contains(".gif")
                        || req.getRequestURI().contains("/static/")
                        || req.getRequestURI().equals(contextPath + "/login-view")
                        || req.getRequestURI().equals(contextPath + "/forgot-password")
                        || req.getRequestURI().equals(contextPath + "/send-email")
                        || req.getRequestURI().equals(contextPath + "/login")) {

                    chain.doFilter(request, response);
                    return;
                } else {
                    resp.sendRedirect(contextPath + "/login-view");
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void destroy() {
    }

    public boolean checkLogined(HttpServletRequest request) {
        HttpSession session = request.getSession();
        CommonContext commonContext= (CommonContext) session.getAttribute(session.getId());
        if (commonContext == null) {
            return false;
        } else {
            return true;
        }
    }
}
