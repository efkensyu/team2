package com.example.demo.team2.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.team2.entity.Team2Questions;
import com.example.demo.team2.form.Team2QuestionForm;
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
		model.addAttribute("team2QuestionForm", new Team2QuestionForm());
		System.out.println("問題作成画面へ遷移");
		return "team2/questions/team2_questions_create";
	}
	
	//問題を作成する
	@PostMapping("/team2/questions/create")
	public String createQuestion
	(@ModelAttribute @Validated Team2QuestionForm team2QuestionForm, BindingResult result, HttpSession session,  
			Model model) {
		//バリデーション
		if (result.hasErrors()) {
			System.out.println("問題作成失敗");
			return "team2/questions/team2_questions_create";
		}
		int userId = (int) session.getAttribute("userId");
		
		//Formの値をEntityに詰め替え
		Team2Questions team2Questions = new Team2Questions();
		team2Questions.setUserId(userId);
		team2Questions.setFieldId(team2QuestionForm.getFieldId());
		team2Questions.setStudyName(team2QuestionForm.getStudyName());
		team2Questions.setQuestionType(team2QuestionForm.getQuestionType());
		team2Questions.setQuestionText(team2QuestionForm.getQuestionText());
		team2Questions.setChoiceA(team2QuestionForm.getChoiceA());
		team2Questions.setChoiceB(team2QuestionForm.getChoiceB());
		team2Questions.setChoiceC(team2QuestionForm.getChoiceC());
		team2Questions.setChoiceD(team2QuestionForm.getChoiceD());
		team2Questions.setCorrectAnswer(team2QuestionForm.getCorrectAnswer());
		team2Questions.setExplanation(team2QuestionForm.getExplanation());
		
		//Entityに保存
		questionsService.save(team2Questions);
		System.out.println("問題を作成する");
		return "redirect:/team2/questions";
	}
	
	
	//問題詳細を表示
	@GetMapping("/team2/questions/{questionId}")
	public String detailQuestion(@PathVariable int questionId, Model model) {
		Team2Questions question = questionsService.findByQuestionId(questionId);
		model.addAttribute("question", question);
		System.out.println("問題詳細画面へ遷移");
		return "team2/questions/team2_questions_detail";
	}
	
	//編集画面を表示
	@GetMapping("/team2/questions/{questionId}/edit")
	public String editQuestion(@PathVariable int questionId, Model model) {
		Team2Questions question = questionsService.findByQuestionId(questionId);
		model.addAttribute("team2QuestionForm", question);
		System.out.println("問題編集画面へ遷移");
		return "team2/questions/team2_questions_edit";
	}
	
	//編集する
	@PostMapping("/team2/questions/{questionId}/edit")
	public String updateQuestion(@PathVariable int questionId, @ModelAttribute Team2Questions team2QuestionForm, HttpSession session) {
		int userId = (int) session.getAttribute("userId");
		team2QuestionForm.setQuestionId(questionId);
		team2QuestionForm.setUserId(userId);
		questionsService.save(team2QuestionForm);
		System.out.println("問題を編集する");
		return "redirect:/team2/questions";
	}
	
	//削除する
	@PostMapping("/team2/questions/{questionId}/delete")
	public String deleteQuestion(@PathVariable int questionId) {
		questionsService.delete(questionId);
		System.out.println("問題を削除する");
		return "redirect:/team2/questions";
	}
		
	
	
	
		
}
