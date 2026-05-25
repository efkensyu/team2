package com.example.demo.team2.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name="team2_study_logs_tbl")
@Data
public class Team2StudyLogs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "study_log_id")
	private int studyLogId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "field_id")
	private int fieldId;

	@Column(name = "study_date")
	private LocalDate studyDate;

	@Column(name = "study_time")
	private int studyTime;

	@Column(name = "study_name")
	private String studyName;

	@Column(name = "study_content")
	private String studyContent;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@ManyToOne
	@JoinColumn(name = "field_id", insertable = false, updatable = false)
	private Team2Fields field;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private Team2User user;
}
