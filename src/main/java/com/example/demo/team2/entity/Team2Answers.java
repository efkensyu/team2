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

import lombok.Data;

@Entity
@Table(name="TEAM2_ANSWERS_TBL")
@Data
public class Team2Answers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int answerId;
	
	@Column(name = "USER_ID")
	private int userId;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", insertable = false, updatable = false)
	private Team2User user;
	
	@Column(name = "QUESTION_ID")
	private int questionId;
	
	@ManyToOne
	@JoinColumn(name = "QUESTION_ID", insertable = false, updatable = false)
	private Team2Questions question;
	
	private String userAnswer;
	
	private int isCorrect;
	
	@CreationTimestamp
	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;
	
}
