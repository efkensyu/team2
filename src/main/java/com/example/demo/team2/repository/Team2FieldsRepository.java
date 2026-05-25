package com.example.demo.team2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.team2.entity.Team2Fields;

public interface Team2FieldsRepository extends JpaRepository <Team2Fields, Integer> {
	//一覧表示
	List<Team2Fields>findAll();
	
}
