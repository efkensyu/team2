package com.example.demo.team2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.team2.entity.Team2Questions;

public interface Team2QuestionsRepository extends JpaRepository <Team2Questions, Integer> {
	//一覧表示
	List<Team2Questions>findAllByOrderByCreatedAtDesc();
	
}
