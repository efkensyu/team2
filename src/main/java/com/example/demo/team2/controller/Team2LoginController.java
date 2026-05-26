package com.example.demo.team2.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.team2.entity.Team2User;
import com.example.demo.team2.form.Team2LoginForm;
import com.example.demo.team2.repository.Team2UserRepository;
import com.example.demo.team2.service.Team2UsersService;

@Controller
public class Team2LoginController {
	
	@Autowired
	private Team2UsersService usersService;
	//ログイン画面表示
		@GetMapping("/team2/login")
		public String index(HttpSession session, Model model) {
			
			Team2LoginForm team2LoginForm = (Team2LoginForm) session.getAttribute("team2LoginForm");
			
			//ログイン済みか
			if (team2LoginForm != null && team2LoginForm.getLoginId() != null) {
				System.out.println("ログイン済み");
				return "redirect:/team2/home";
			}
			
			model.addAttribute("team2LoginForm", new Team2LoginForm());
			
		    System.out.println("ログイン画面へ遷移");

		    return "team2/users/team2_login";
		}
		
		//ログイン
		@PostMapping("/team2/login")
		public String login (@ModelAttribute("team2LoginForm") @Validated Team2LoginForm team2LoginForm, BindingResult result, HttpSession session, Model model) {
			
			//バリデーションエラー
			if (result.hasErrors()) {
				System.out.println("ログイン失敗");
				return "team2/users/team2_login";
			}
			
			Team2User user = usersService.authenticate(team2LoginForm.getLoginId(), team2LoginForm.getPassword());
			if (user == null) {
				model.addAttribute("errorMessage", "IDまたはパスワードが正しくありません");
				return "team2/users/team2_login";
			}
			
			//ログイン成功
			session.setAttribute("team2LoginForm", team2LoginForm);
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userName", user.getUserName());
			System.out.println("ログイン成功");
			return "redirect:/team2/home";
		}
		
		//ログアウト
		@PostMapping("/team2/logout")
		public String logout(HttpSession session) {
			System.out.println("ログアウト");
			session.invalidate();
			return "redirect:/team2/login";
		}
}
