package com.example.demo.teamX.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.teamX.entity.Bumon;
import com.example.demo.teamX.service.DBService;

import lombok.RequiredArgsConstructor;				
		
@Controller		
@RequiredArgsConstructor
public class DBController {		
	private final DBService dbService3;			
		
	@GetMapping("/dbsample")			
	public String index () {
		return "teamX/DB1_1";		
	}	
	@PostMapping("/dbsample")			
	public String send (@RequestParam("BumonCd") String code,Model model) {
		List<Bumon> userDataList = dbService3.findByBumonCd(code);
		model.addAttribute("userDataList",userDataList);
		return "teamX/DB1_2";		
	}							
}				
