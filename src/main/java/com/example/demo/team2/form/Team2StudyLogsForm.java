package com.example.demo.team2.form;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Team2StudyLogsForm {
	private int studyLogId;
	private int userId;
	
	@NotNull(message = "分野を選択してください")
	private int fieldId;
	
	@NotNull(message = "学習日を入力してください")
	private LocalDate studyDate;
	
	@NotBlank(message = "学習名を入力してください")
	private String studyName;
	
	private String studyContent;
	
	private int studyHour;
	private int studyMinute;
}
