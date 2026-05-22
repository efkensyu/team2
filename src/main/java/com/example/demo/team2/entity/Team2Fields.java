package com.example.demo.team2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name="team2_fields_tbl")
@Data
public class Team2Fields {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fieldId;
	
	private String fieldName;
}

