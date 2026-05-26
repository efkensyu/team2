package com.example.demo.team2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.team2.entity.Team2Answers;
import com.example.demo.team2.repository.Team2AnswersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Team2AnswersService {
	private final Team2AnswersRepository answersRepository;
	
	//ユーザの回答を保存
	public void save(Team2Answers answer) {
		answersRepository.save(answer);	
		}
	
	//ユーザの回答一覧を取得
	public List<Team2Answers>findByUserId(int userId){
		return answersRepository.findByUserIdOrderByCreatedAtDesc(userId);
	}
	
	//回答詳細を取得
	public Team2Answers findByAnswerId(int answerId) {
		return answersRepository.findById(answerId)
				.orElseThrow(() -> new RuntimeException("回答が見つかりません"));
	}
}