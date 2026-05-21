package com.example.demo.team2.controller;

import java.time.LocalDateTime;
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
import com.example.demo.team2.form.Team2RegisterForm;
import com.example.demo.team2.repository.Team2UserRepository;

@Controller
public class Team2RegisterController {
	
	@Autowired
	private Team2UserRepository userRepository;
    
	@GetMapping("/team2/register")
	public String index(@ModelAttribute Team2RegisterForm team2RegisterForm) {

	    System.out.println("ユーザ新規登録画面へ遷移");

	    return "team2/users/team2_register";
	}
	
	@PostMapping("/team2/register")
	public String register (@ModelAttribute @Validated Team2RegisterForm team2RegisterForm, BindingResult result, HttpSession session, Model model) {
		
		//バリデーション
		if (result.hasErrors()) {
			System.out.println("新規登録失敗");
			return "team2/users/team2_register";
		}
		
		//ログインIDが重複しているか
		List<Team2User> users = userRepository.findByLoginId(team2RegisterForm.getLoginId());
		if (users.size() > 0) {
			model.addAttribute("errorMessage", "このIDはすでに使用されています");
			System.out.println("ログインID重複");
			return "team2/users/team2_register";
		}
		
		//パスワードとパスワード（確認）が一致しているか
		if (!team2RegisterForm.getPassword().equals(team2RegisterForm.getConfirmPassword())) {
			System.out.println("パスワード不一致");
			model.addAttribute("confirmPasswordError", "パスワードと確認用パスワードが一致しません");
			return "team2/users/team2_register";
		}
		
		//INSERT
		Team2User user = new Team2User();
		user.setLoginId(team2RegisterForm.getLoginId());
		user.setPassword(team2RegisterForm.getPassword());
		user.setUserName(team2RegisterForm.getUserName());
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		user.setIsDeleted(0);
		userRepository.save(user);
		System.out.println("登録成功");
		
		//登録後
		Team2LoginForm loginForm = new Team2LoginForm();
		loginForm.setLoginId(team2RegisterForm.getLoginId());
		loginForm.setPassword(team2RegisterForm.getPassword());
		session.setAttribute("team2LoginForm", loginForm);
		System.out.println("新規登録成功");
		return "redirect:/team2/home";
		}
}
