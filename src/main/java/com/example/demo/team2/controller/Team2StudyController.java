package com.example.demo.team2.controller;

import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.team2.entity.Team2StudyLogs;
import com.example.demo.team2.entity.Team2User;
import com.example.demo.team2.form.Team2LoginForm;
import com.example.demo.team2.form.Team2StudyLogsForm;
import com.example.demo.team2.repository.Team2StudyLogsRepository;
import com.example.demo.team2.repository.Team2UserRepository;
import com.example.demo.team2.service.Team2StudyLogsService;

@Controller
public class Team2StudyController {

	@Autowired
	private Team2StudyLogsService studyLogsService;

	//一覧表示
	@GetMapping("/team2/logs")
	public String logs(@RequestParam(required = false) String keyword, Model model) {

		List<Team2StudyLogs> logs = studyLogsService.searchLogs(keyword);

		model.addAttribute("logs", logs);
		model.addAttribute("keyword", keyword);

		return "team2/study_logs/team2_logs";
	}

	//登録画面表示
	@GetMapping("/team2/logs/create")
	public String create(@ModelAttribute Team2StudyLogsForm team2StudyLogsForm, HttpServletRequest request,
			HttpSession session) {

		team2StudyLogsForm.setStudyDate(LocalDate.now());

		//遷移前のURLを取得
		String referer = request.getHeader("Referer");
		session.setAttribute("backUrl", referer);

		return "team2/study_logs/team2_logs_create";
	}

	//登録する
	@PostMapping("/team2/logs/create")
	public String saveStudyLog(@ModelAttribute @Validated Team2StudyLogsForm team2StudyLogsForm, BindingResult result,
			HttpSession session, RedirectAttributes redirectAttributes, Model model) {

		//バリデーション
		if (result.hasErrors()) {
			System.out.println("バリデーションエラー");
			return "team2/study_logs/team2_logs_create";
		}

		//0分は登録できない
		if (team2StudyLogsForm.getStudyHour() == 0 && team2StudyLogsForm.getStudyMinute() == 0) {
			System.out.println("バリデーションエラー（学習時間）");
			model.addAttribute("studyTimeError", "学習時間を入力してください");
			return "team2/study_logs/team2_logs_create";
		}

		//2010年より前は登録できない
		if (team2StudyLogsForm.getStudyDate().isBefore(LocalDate.of(2010, 1, 1))) {
			System.out.println("バリデーションエラー（学習日）");
			model.addAttribute("studyDateError", "2010年以降の日付を入力してください");
			return "team2/study_logs/team2_logs_create";
		}

		int userId = (int) session.getAttribute("userId");
		studyLogsService.save(team2StudyLogsForm, userId);
		System.out.println("学習記録登録成功");
		redirectAttributes.addFlashAttribute("successMessage", "学習記録を登録しました");
		return "redirect:/team2/logs";
	}

	//詳細
	@GetMapping("/team2/logs/{id}")
	public String detail(@PathVariable int id, HttpServletRequest request, HttpSession session, Model model) {

		String referer = request.getHeader("Referer");
		if (referer != null) {
			session.setAttribute("editBackUrl", referer);
		}

		Team2StudyLogs log = studyLogsService.findById(id);
		model.addAttribute("log", log);
		System.out.println("学習記録詳細画面へ遷移");
		return "team2/study_logs/team2_logs_detail";
	}

	//編集画面
	@GetMapping("/team2/logs/{id}/edit")
	public String edit(@PathVariable int id, Model model) {

		Team2StudyLogs log = studyLogsService.findById(id);
		Team2StudyLogsForm form = new Team2StudyLogsForm();
		form.setStudyLogId(log.getStudyLogId());
		form.setFieldId(log.getFieldId());
		form.setStudyDate(log.getStudyDate());
		form.setStudyName(log.getStudyName());
		form.setStudyContent(log.getStudyContent());
		form.setStudyHour(log.getStudyTime() / 60);
		form.setStudyMinute(log.getStudyTime() % 60);
		model.addAttribute("team2StudyLogsForm", form);
		return "team2/study_logs/team2_logs_edit";
	}

	//編集
	@PostMapping("/team2/logs/{id}/edit")
	public String update(@PathVariable int id, @ModelAttribute @Validated Team2StudyLogsForm team2StudyLogsForm,
			BindingResult result, RedirectAttributes redirectAttributes, Model model) {

		//バリデーション
		if (result.hasErrors()) {
			System.out.println("編集失敗（バリデーション）");
			return "team2/study_logs/team2_logs_edit";
		}

		// 0分は登録できない
		if (team2StudyLogsForm.getStudyHour() == 0 && team2StudyLogsForm.getStudyMinute() == 0) {
			System.out.println("バリデーションエラー（学習時間）");
			model.addAttribute("studyTimeError", "学習時間を入力してください");
			return "team2/study_logs/team2_logs_edit";
		}
		
		//2010年より前は登録できない
		if (team2StudyLogsForm.getStudyDate().isBefore(LocalDate.of(2010, 1, 1))) {
			System.out.println("バリデーションエラー（学習日）");
			model.addAttribute("studyDateError", "2010年以降の日付を入力してください");
			return "team2/study_logs/team2_logs_create";
		}

		team2StudyLogsForm.setStudyLogId(id);
		studyLogsService.update(team2StudyLogsForm);
		System.out.println("学習更新成功");
		redirectAttributes.addFlashAttribute("successMessage", "学習記録を更新しました");
		return "redirect:/team2/logs/" + id;
	}

	//削除
	@PostMapping("/team2/logs/{id}/delete")
	public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
		studyLogsService.delete(id);
		System.out.println("学習削除成功");
		redirectAttributes.addFlashAttribute("successMessage", "学習記録を削除しました");
		return "redirect:/team2/logs";
	}

}
