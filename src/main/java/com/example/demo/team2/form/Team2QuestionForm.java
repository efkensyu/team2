package com.example.demo.team2.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class Team2QuestionForm {
	@NotBlank
	private String fieldName;
	
	private String studyName;
	
	@NotBlank
	private String questionType;
	
	@NotBlank
	@Size(max = 1000)
	private String questionText;
	
	@Size(max = 100)
	private String choiceA;
	@Size(max = 100)
	private String choiceB;
	@Size(max = 100)
	private String choiceC;
	@Size(max = 100)
	private String choiceD;
	
	@NotBlank
	@Size(max = 1000)
	private String correctAnswer;
	private String explanation;
	
}
