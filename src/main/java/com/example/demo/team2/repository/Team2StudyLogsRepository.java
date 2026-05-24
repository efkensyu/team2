package com.example.demo.team2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.team2.entity.Team2StudyLogs;

@Repository
public interface Team2StudyLogsRepository extends JpaRepository<Team2StudyLogs, Integer> {
	//一覧表示
	List<Team2StudyLogs> findAllByOrderByStudyDateDesc();
	
	//ユーザIDで検索
	List<Team2StudyLogs> findByUserIdOrderByStudyDateDesc(int userId);
	
	//1件取得
	@EntityGraph(attributePaths = {"user", "field"})
	Optional<Team2StudyLogs> findByStudyLogId(int studyLogId);
	
	//自分の全記録
	@EntityGraph(attributePaths = {"user", "field"})
	List<Team2StudyLogs> findByUserId(int userId);
}
