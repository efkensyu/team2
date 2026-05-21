package com.example.demo.team2.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name="team2_users_tbl")
@Data
public class Team2User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	private String loginId;
	private String password;
	private String userName;
	private int isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
