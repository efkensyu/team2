package com.example.demo.team2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.team2.entity.Team2Answers;

public interface Team2AnswersRepository extends JpaRepository <Team2Answers, Integer>{
	List<Team2Answers> findByUserIdOrderByCreatedAtDesc(int userId);
	
	//回答問題数
	int countByUserId(int userId);
	
	//問題を削除
	void deleteByQuestionId(int questionId);
}