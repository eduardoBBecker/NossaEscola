package com.dev.nossaescola.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("usuarioLogado") != null);
        String loginURI = request.getContextPath() + "/login";

        if (loggedIn || request.getRequestURI().equals(loginURI)) {
            return true;
        } else {
            response.sendRedirect(loginURI);
            return false;
        }
    }
}
