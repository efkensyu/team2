package com.example.demo.team2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sample {
	@GetMapping("/team2hello1")	
	public String index() {	
		return "Hello World2!";
	}
}