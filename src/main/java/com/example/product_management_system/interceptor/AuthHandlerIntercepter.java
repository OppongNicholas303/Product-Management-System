package com.example.product_management_system.interceptor;

import com.example.product_management_system.exception.NotFound;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class AuthHandlerIntercepter implements HandlerInterceptor {
    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ) throws Exception
    {
        log.info("AuthIntercepter afterCompletion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) throws Exception
    {
        log.info("AuthIntercepter postHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        // Extract Authorization header
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if ("admin".equalsIgnoreCase(token)) {
                log.info("Access granted for role: {}", token);
                return true;
            } else {
                log.warn("Access denied for role: {}", token);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN); // Forbidden
                response.getWriter().write("Access Denied: Insufficient permissions.");
                return false;
            }
        } else {
            log.warn("Authorization header not found or malformed.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Unauthorized
            response.getWriter().write("Unauthorized: Missing or invalid Authorization header.");
            return false;
        }
    }
}
