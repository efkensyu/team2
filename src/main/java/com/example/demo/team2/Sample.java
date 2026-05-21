package com.example.demo.team2;

import org.springframework.web.bind.annotation.GetMapping;

public class Sample {
	@GetMapping("/team2/hello1")	
	public String index() {	
		return "Hello World2!";
	}
}
