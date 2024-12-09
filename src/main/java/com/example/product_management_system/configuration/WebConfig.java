package com.example.product_management_system.configuration;

import com.example.product_management_system.interceptor.AuthHandlerIntercepter;
import com.example.product_management_system.interceptor.LogHandlerIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        WebMvcConfigurer.super.addInterceptors(registry);

        registry.addInterceptor(new LogHandlerIntercepter()).order(1);
        registry.addInterceptor(new AuthHandlerIntercepter()).order(2);

    }

//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer.addPathPrefix("/api/v1",
//                c -> c.isAnnotationPresent(RestController.class));
//    }

}
