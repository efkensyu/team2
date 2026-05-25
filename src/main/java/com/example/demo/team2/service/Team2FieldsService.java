package com.example.demo.team2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.team2.entity.Team2Fields;
import com.example.demo.team2.repository.Team2FieldsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Team2FieldsService {
	
	private final Team2FieldsRepository fieldsRepository;
	
	//全件取得
	public List<Team2Fields> findAll(){
		return fieldsRepository.findAll();
	}
}
