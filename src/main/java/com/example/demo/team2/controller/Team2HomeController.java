package com.example.demo.team2.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.team2.entity.Team2User;
import com.example.demo.team2.form.Team2LoginForm;
import com.example.demo.team2.repository.Team2UserRepository;

@Controller
public class Team2HomeController {

	@Autowired
	private Team2UserRepository userRepository;

	@GetMapping("/team2/home")
	public String index(HttpSession session, Model model) {

		//ログイン情報を取得
		Team2LoginForm team2LoginForm = (Team2LoginForm) session.getAttribute("team2LoginForm");
		List<Team2User> users = userRepository.findAll();
		model.addAttribute("users", users);

		model.addAttribute("team2LoginForm", team2LoginForm);
		System.out.println("ホーム画面へ遷移：" + team2LoginForm.getLoginId());
		return "team2/team2_home";
	}

	//問題一覧
	@GetMapping("/team2/questions")
	public String questions(HttpSession session, Model model) {
		Team2LoginForm team2LoginForm = (Team2LoginForm) session.getAttribute("team2LoginForm");
	    model.addAttribute("team2LoginForm", team2LoginForm);
		System.out.println("問題一覧画面へ遷移");
		return "team2/questions/team2_questions";
	}

	//採点結果一覧
	@GetMapping("/team2/answers")
	public String answers(HttpSession session, Model model) {
		Team2LoginForm team2LoginForm = (Team2LoginForm) session.getAttribute("team2LoginForm");
	    model.addAttribute("team2LoginForm", team2LoginForm);
		System.out.println("採点結果一覧画面へ遷移");
		return "team2/answers/team2_answers";
	}

}
