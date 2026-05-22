package com.example.demo.team2.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name="TEAM2_QUESTIONS_TBL")
@Data
public class Team2Questions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;
	
	private int userId;
	private int fieldId;
	private String studyName;
	private String questionType;
	private String questionText;
	private String choiceA;
	private String choiceB;
	private String choiceC;
	private String choiceD;
	private String correctAnswer;
	private String explanation;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}

