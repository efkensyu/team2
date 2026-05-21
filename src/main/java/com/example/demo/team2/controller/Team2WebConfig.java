package com.example.demo.team2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//WebConfig.java（Interceptorを登録）
@Configuration
public class Team2WebConfig implements WebMvcConfigurer {

 @Autowired
 private Team2LoginInterceptor loginInterceptor;

 @Override
 public void addInterceptors(InterceptorRegistry registry) {
     registry.addInterceptor(loginInterceptor)
             .addPathPatterns("/team2/**")    // 対象パス
             .excludePathPatterns("/team2/login", "/team2/register"); // ログイン画面は除外

     
 }
}