package com.example.demo.team2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.team2.entity.Team2StudyLogs;

@Repository
public interface Team2StudyLogsRepository extends JpaRepository<Team2StudyLogs, Integer>{
}
