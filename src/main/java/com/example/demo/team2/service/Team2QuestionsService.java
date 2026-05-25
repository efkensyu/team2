package com.example.demo.team2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.team2.entity.Team2Questions;
import com.example.demo.team2.repository.Team2QuestionsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Team2QuestionsService {
	
	private final Team2QuestionsRepository questionsRepository;
	
	//全件取得
	public List<Team2Questions> findAll(){
		return questionsRepository.findAllByOrderByCreatedAtDesc();
	}
	
	public void save(Team2Questions question) {
		questionsRepository.save(question);
	}
	
	
	
}
