package com.example.demo.team2.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.team2.entity.Team2Questions;
import com.example.demo.team2.service.Team2QuestionsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class Team2QuestionController {
	
	private final Team2QuestionsService questionsService;
	
	//問題一覧を表示
	@GetMapping("/team2/questions")
	public String show(Model model) {
		List<Team2Questions> questions = questionsService.findAll();
		model.addAttribute("questions", questions);
		System.out.println("問題一覧画面へ遷移");
		return "team2/questions/team2_questions";
		
	}
	
	//問題作成画面を表示
	@GetMapping("/team2/questions/create")
	public String createQuestion(Model model) {
		model.addAttribute("team2QuestionForm", new Team2Questions());
		return "team2/questions/team2_questions_create";
	}
	
	//問題を作成する
	@PostMapping("/team2/questions/create")
	public String createQuestion(@ModelAttribute Team2Questions team2QuestionForm) {
		questionsService.save(team2QuestionForm);
		return "redirect:/team2/questions";
	}
	
	

	
	//問題詳細を表示
	@PostMapping("/team2/questions/detail")
	public String detailQuestion() {
		return "team2/questions/team2_questions_detail";
	}
	
	//問題を編集
		@PutMapping("/team2/questions/edit")
		public String editQuestion() {
			return "team2/questions/team2_questions_edit";
		}
}
