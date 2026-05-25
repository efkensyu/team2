package com.example.demo.team2.form;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Team2StudyLogsForm {
	private int studyLogId;
	private int userId;
	
	@Min(value = 1, message = "分野を選択してください")
	private int fieldId;
	
	@NotNull(message = "学習日を入力してください")
	@PastOrPresent(message = "未来の日付は入力できません")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate studyDate;
	
	@NotBlank(message = "学習名を入力してください")
	private String studyName;
	
	@Size(max = 300, message = "学習内容は300字で記述してください")
	private String studyContent;
	
	private int studyHour;
	private int studyMinute;
}
