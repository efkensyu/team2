package com.example.demo.team2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.team2.form.Team2LoginForm;

@Component
public class Team2LoginInterceptor implements HandlerInterceptor {
	@Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
		
		System.out.println("リクエストURI: " + request.getRequestURI());
 
        HttpSession session = request.getSession(false); // セッションが無ければnullを返す
 
        // セッションが存在しない、またはログイン情報がない場合はログイン画面へ
        if (session == null) {
            System.out.println("セッションなし → ログイン画面へ");
            response.sendRedirect(request.getContextPath() + "/team2/login");
            return false;
        }
 
        Team2LoginForm loginForm = (Team2LoginForm) session.getAttribute("team2LoginForm");
 
        if (loginForm == null || loginForm.getLoginId() == null) {
            System.out.println("未ログイン → ログイン画面へ");
            response.sendRedirect(request.getContextPath() + "/team2/login");
            return false;
        }
 
        System.out.println("ログイン済み確認: " + loginForm.getLoginId());
        return true; // 処理を続ける
    }
	
}