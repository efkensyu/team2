package com.example.demo.team2.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.team2.entity.Team2StudyLogs;
import com.example.demo.team2.form.Team2StudyLogsForm;
import com.example.demo.team2.repository.Team2StudyLogsRepository;

@Service
public class Team2StudyLogsService {

	@Autowired
	private Team2StudyLogsRepository studyLogsRepository;

	//全件取得
	public List<Team2StudyLogs> findAll() {
		return studyLogsRepository.findAllByOrderByStudyDateDesc();
	}

	//1件登録
	public Team2StudyLogs findById(int studyLogId) {
		return studyLogsRepository.findByStudyLogId(studyLogId).orElse(null);
	}

	//登録
	public void save(Team2StudyLogsForm form, int userId) {
		Team2StudyLogs log = new Team2StudyLogs();
		log.setUserId(userId);
		log.setFieldId(form.getFieldId());
		log.setStudyDate(form.getStudyDate());
		log.setStudyName(form.getStudyName());
		log.setStudyTime(form.getStudyHour() * 60 + form.getStudyMinute());
		log.setStudyContent(form.getStudyContent());
		log.setCreatedAt(LocalDateTime.now());
		log.setUpdatedAt(LocalDateTime.now());
		studyLogsRepository.save(log);
	}

	//更新
	public void update(Team2StudyLogsForm form) {
		Team2StudyLogs log = studyLogsRepository.findById(form.getStudyLogId()).orElse(null);
		if (log == null)
			return;
		log.setFieldId(form.getFieldId());
		log.setStudyDate(form.getStudyDate());
		log.setStudyName(form.getStudyName());
		log.setStudyContent(form.getStudyContent());
		log.setStudyTime(form.getStudyHour() * 60 + form.getStudyMinute());
		log.setUpdatedAt(LocalDateTime.now());
		studyLogsRepository.save(log);
	}

	//削除
	public void delete(int studyLogId) {
		studyLogsRepository.deleteById(studyLogId);
	}

	//自分の記録一覧
	public List<Team2StudyLogs> findByUserId(int userId) {
		return studyLogsRepository.findByUserIdOrderByStudyDateDesc(userId);
	}

	//総学習時間
	public int getTotalStudyTime(int userId) {
		return studyLogsRepository.findByUserId(userId).stream().mapToInt(Team2StudyLogs::getStudyTime).sum();
	}

	//今月の学習時間
	public int getMonthlyStudyTime(int userId) {
		LocalDate now = LocalDate.now();
		LocalDate start = now.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate end = now.with(TemporalAdjusters.lastDayOfMonth());
		return studyLogsRepository.findByUserId(userId).stream()
				.filter(l -> !l.getStudyDate().isBefore(start) && !l.getStudyDate().isAfter(end))
				.mapToInt(Team2StudyLogs::getStudyTime).sum();
	}

	//今週の学習時間
	public int getWeeklyStudyTime(int userId) {
		LocalDate now = LocalDate.now();
		LocalDate start = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
	    LocalDate end = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		return studyLogsRepository.findByUserId(userId).stream()
				.filter(l -> !l.getStudyDate().isBefore(start) && !l.getStudyDate().isAfter(end))
				.mapToInt(Team2StudyLogs::getStudyTime).sum();
	}

	//今日の学習時間
	public int getDailyStudyTime(int userId) {
		LocalDate today = LocalDate.now();
		return studyLogsRepository.findByUserId(userId).stream().filter(l -> l.getStudyDate().equals(today)).mapToInt(Team2StudyLogs::getStudyTime).sum();
	}

}
