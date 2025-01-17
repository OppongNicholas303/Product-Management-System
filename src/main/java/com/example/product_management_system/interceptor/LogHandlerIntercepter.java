package com.example.product_management_system.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class LogHandlerIntercepter implements HandlerInterceptor {
    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ) throws Exception
    {
        log.info("LogHandlerIntercepter afterCompletion");
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
        log.info("LogHandlerIntercepter postHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception
    {

        log.info("LogHandlerIntercepter.preHandle");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
