package com.example.demo.team2.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.team2.entity.Team2Answers;
import com.example.demo.team2.entity.Team2Questions;
import com.example.demo.team2.service.Team2AnswersService;
import com.example.demo.team2.service.Team2QuestionsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class Team2AnswerController {
	private final Team2QuestionsService questionsService;
	private final Team2AnswersService answersService;
	
	//回答画面を表示
	@GetMapping ("/team2/questions/{questionId}/answer")
	public String answer(@PathVariable int questionId, Model model) {
		Team2Questions question = questionsService.findByQuestionId(questionId);
		model.addAttribute("question", question);
		System.out.println("回答画面を表示");
		return "team2/answers/team2_answer";
	}
	
	//回答を送信して採点
	@PostMapping("/team2/questions/{questionId}/answer")
	public String answer(@PathVariable int questionId, 
			@RequestParam String userAnswer,
			HttpSession session,
			Model model) {
		Team2Questions question = questionsService.findByQuestionId(questionId);
		int userId = (int) session.getAttribute("userId");
		
		//正誤判定
		boolean isCorrect = question.getCorrectAnswer().trim()
				.equalsIgnoreCase(userAnswer.trim());
		
		//回答を保存
		Team2Answers answer = new Team2Answers();
		answer.setUserId(userId);
		answer.setQuestionId(questionId);
		answer.setUserAnswer(userAnswer);
		answer.setIsCorrect(isCorrect ? 1 : 0);
		answersService.save(answer);
		
		//採点結果画面に渡す
		model.addAttribute("question", question);
		model.addAttribute("userAnswer", userAnswer);
		model.addAttribute("isCorrect", isCorrect);
		
		System.out.println("採点結果画面を表示");
		return "team2/answers/team2_answer_result";
	}
	
	
	//採点結果詳細を表示
	@GetMapping("/team2/answers/{answerId}")
	public String detailAnswer(@PathVariable int answerId, Model model) {
		Team2Answers answer = answersService.findByAnswerId(answerId);
		model.addAttribute("answer", answer);
		System.out.println("採点詳細画面へ遷移");
		return "team2/answers/team2_answers_detail";	
	}
	
	//採点結果一覧
	@GetMapping("/team2/answers")
	public String answers(HttpSession session, Model model) {
		int userId = (int) session.getAttribute("userId");
		model.addAttribute("answers", answersService.findByUserId(userId));
		System.out.println("採点結果一覧画面へ遷移");
		return "team2/answers/team2_answers_list";
	}
	
	
	
	
	
}
