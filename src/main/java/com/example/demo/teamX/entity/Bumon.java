package com.example.demo.teamX.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity		
@Table(name="BUMON_TBL")
@Data
public class Bumon {		
	@Id	
	private String bumonCd;
	private String bumonNm;		
}		
