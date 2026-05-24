package com.example.demo.team2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Team2QuestionController {
	
	//問題一覧を表示
	@GetMapping("/team2/questions")
	public String index() {
		return "team2/questions/team2_questions";
	}
	
	//問題作成を表示
	@PostMapping("/team2/questions/create")
	public String createQuestion() {
		return "team2/questions/team2_questions_create";
	}
	
	//問題詳細を表示
	@PostMapping("/team2/questions")
	public String detailQuestion() {
		return "team2/questions/team2_questions_detail";
	}
	
	//問題を編集
		@PostMapping("/team2/questions/detail")
		public String editQuestion() {
			return "team2/questions/team2_questions_edit";
		}
	
}
