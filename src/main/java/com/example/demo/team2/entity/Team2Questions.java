package com.example.demo.team2.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="TEAM2_QUESTIONS_TBL")

@Data
public class Team2Questions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;
	
	@Column(name = "USER_ID")
	private int userId;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", insertable = false, updatable = false)
	private Team2User user;
	
	
	@Column(name = "FIELD_ID")
	private int fieldId;
	
	@ManyToOne
	@JoinColumn(name = "FIELD_ID", insertable = false, updatable = false)
	private Team2Fields field;
	
	
	private String studyName;
	private String questionType;
	
	@Column(name = "QUESTION_TEXT")
	private String questionText;
	
	@Column(name = "CHOICE_A")
	private String choiceA;
	
	@Column(name = "CHOICE_B")
	private String choiceB;
	
	@Column(name = "CHOICE_C")
	private String choiceC;
	
	@Column(name = "CHOICE_D")
	private String choiceD;
	
	private String correctAnswer;
	private String explanation;
	
	@CreationTimestamp
	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "UPDATED_AT")
	private LocalDateTime updatedAt;
}

