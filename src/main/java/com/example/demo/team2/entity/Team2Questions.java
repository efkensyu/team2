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
@Table(name="team2_questions_tbl")

@Data
public class Team2Questions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private int questionId;

	@Column(name = "user_id")
	private int userId;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private Team2User user;

	@Column(name = "field_id")
	private int fieldId;

	@ManyToOne
	@JoinColumn(name = "field_id", insertable = false, updatable = false)
	private Team2Fields field;

	@Column(name = "study_name")
	private String studyName;

	@Column(name = "question_type")
	private String questionType;

	@Column(name = "question_text")
	private String questionText;

	@Column(name = "choice_a")
	private String choiceA;

	@Column(name = "choice_b")
	private String choiceB;

	@Column(name = "choice_c")
	private String choiceC;

	@Column(name = "choice_d")
	private String choiceD;

	@Column(name = "correct_answer")
	private String correctAnswer;

	@Column(name = "explanation")
	private String explanation;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}

