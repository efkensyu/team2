package com.example.demo.team2.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class Team2QuestionForm {
	
	@Min(value = 1,message = "分野を選択してください。")
	private int fieldId;
	
	private String studyName;
	
	@NotBlank(message = "問題形式を選択してください。")
	private String questionType;
	
	@NotBlank(message = "問題文を記入してください。")
	@Size(max = 1000,message = "問題文は最大1000字です。")
	private String questionText;
	
	@Size(max = 100,message = "選択肢は最大100字です。")
	private String choiceA;
	@Size(max = 100,message = "選択肢は最大100字です。")
	private String choiceB;
	@Size(max = 100,message = "選択肢は最大100字です。")
	private String choiceC;
	@Size(max = 100,message = "選択肢は最大100字です。")
	private String choiceD;
	
	@NotBlank(message = "正解を選択してください。")
	@Size(max = 1000,message = "解答は最大1000字です。")
	private String correctAnswer;
	private String explanation;
	
}
