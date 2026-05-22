package com.example.demo.team2.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name="TEAM2_STUDY_LOGS_TBL")
@Data
public class Team2StudyLogs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studyLogId;
	
	private int userId;
	private int fieldId;
	private LocalDate studyDate;
	private String studyName;
	private String studyContent;
	private LocalDateTime  createdAt;
	private LocalDateTime  updatedAt;
}
