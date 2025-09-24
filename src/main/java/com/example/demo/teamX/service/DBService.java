package com.example.demo.teamX.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.teamX.entity.Bumon;
import com.example.demo.teamX.repositories.BumonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DBService {
	private final BumonRepository repository;			
		
	public List<Bumon> findByBumonCd(String code){				
		return repository.findBumonCd(code);
	}	
}
