package com.example.demo.team2.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.team2.entity.Team2User;
import com.example.demo.team2.form.Team2LoginForm;
import com.example.demo.team2.form.Team2StudyLogsForm;
import com.example.demo.team2.repository.Team2StudyLogsRepository;
import com.example.demo.team2.repository.Team2UserRepository;

@Controller
public class Team2StudyController {

	@Autowired
	private Team2StudyLogsRepository studyLogRepository;
	
	//一覧
	@GetMapping("/team2/logs")
	public String show(@ModelAttribute Team2StudyLogsForm team2StudyLogForm) {
		System.out.println("タイムライン画面へ遷移");
		return "team2/study_logs/team2_logs";
	}
	
	//作成
	@GetMapping("/test2/logs/create")
	public String create(@ModelAttribute Team2StudyLogsForm team2StudyLogForm) {
		System.out.println("学習記録登録画面へ遷移");
		return "team2/study_logs/team2_logs_create";
	}

}
