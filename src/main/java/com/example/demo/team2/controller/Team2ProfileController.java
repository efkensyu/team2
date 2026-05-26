package com.example.demo.team2.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.team2.entity.Team2StudyLogs;
import com.example.demo.team2.entity.Team2User;
import com.example.demo.team2.form.Team2LoginForm;
import com.example.demo.team2.form.Team2PasswordEditForm;
import com.example.demo.team2.form.Team2ProfileDeleteForm;
import com.example.demo.team2.form.Team2ProfileEditForm;
import com.example.demo.team2.repository.Team2UserRepository;
import com.example.demo.team2.service.Team2StudyLogsService;
import com.example.demo.team2.service.Team2UsersService;

@Controller
public class Team2ProfileController {

	@Autowired
	private Team2UsersService usersService;

	@Autowired
	private Team2StudyLogsService studyLogsService;

	//プロフィール画面表示
	@GetMapping("/team2/profile")
	public String profile(HttpSession session, Model model) {
		int userId = (int) session.getAttribute("userId");
		Team2User user = usersService.findById(userId);

		//集計
		int total = studyLogsService.getTotalStudyTime(userId);
		int monthly = studyLogsService.getMonthlyStudyTime(userId);
		int weekly = studyLogsService.getWeeklyStudyTime(userId);
		int daily = studyLogsService.getDailyStudyTime(userId);

		//自分の学習記録一覧
		List<Team2StudyLogs> myLogs = studyLogsService.findByUserId(userId);
		model.addAttribute("user", user);
		model.addAttribute("myLogs", myLogs);

		model.addAttribute("totalHours", total / 60);
		model.addAttribute("totalMinutes", total % 60);

		model.addAttribute("monthlyHours", monthly / 60);
		model.addAttribute("monthlyMinutes", monthly % 60);

		model.addAttribute("weeklyHours", weekly / 60);
		model.addAttribute("weeklyMinutes", weekly % 60);

		model.addAttribute("dailyHours", daily / 60);
		model.addAttribute("dailyMinutes", daily % 60);

		System.out.println("プロフィール画面へ遷移");
		return "team2/users/team2_profile";
	}

	//プロフィール編集画面
	@GetMapping("/team2/profile/edit")
	public String editProfle(HttpSession session, Model model) {
		int userId = (int) session.getAttribute("userId");
		Team2User user = usersService.findById(userId);

		Team2ProfileEditForm form = new Team2ProfileEditForm();
		form.setUserId(user.getUserId());
		form.setUserName(user.getUserName());
		form.setLoginId(user.getLoginId());

		model.addAttribute("team2ProfileEditForm", form);
		return "team2/users/team2_profile_edit";
	}

	//更新
	@PostMapping("/team2/profile/edit")
	public String updateProfile(@ModelAttribute @Validated Team2ProfileEditForm team2ProfileEditForm, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session, Model model) {

		//バリデーション
		if (result.hasErrors()) {
			System.out.println("プロフィール編集失敗（バリデーション）");
			return "team2/users/team2_profile_edit";
		}

		//ログインID重複
		if (usersService.existsByLoginId(team2ProfileEditForm.getLoginId(), team2ProfileEditForm.getUserId())) {
			System.out.println("プロフィール編集失敗（ログインID重複）");
			model.addAttribute("loginIdError", "このログインIDは既に使用されています");
			return "team2/users/team2_profile_edit";
		}

		usersService.updateProfile(team2ProfileEditForm);
		session.setAttribute("userName", team2ProfileEditForm.getUserName());
		System.out.println("プロフィール更新成功");
		redirectAttributes.addFlashAttribute("successMessage", "プロフィールを更新しました");

		return "redirect:/team2/profile";
	}

	//パスワード変更画面表示
	@GetMapping("/team2/profile/password")
	public String editPassword(@ModelAttribute Team2PasswordEditForm team2PasswordEditForm) {
		return "team2/users/team2_profile_password";
	}

	//パスワード変更
	@PostMapping("/team2/profile/password")
	public String updatePassword(@ModelAttribute @Validated Team2PasswordEditForm team2PasswordEditForm, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		int userId = (int) session.getAttribute("userId");

		//バリデーション
		if (result.hasErrors()) {
			System.out.println("変更失敗（バリデーション）");
			return "team2/users/team2_profile_password";
		}

		//現在のパスワードが違う
		if (!usersService.checkPassword(userId, team2PasswordEditForm.getCurrentPassword())) {
			System.out.println("変更失敗（現在のパスワードが違う）");
			model.addAttribute("currentPasswordError", "現在のパスワードが正しくありません");
			return "team2/users/team2_profile_password";
		}

		//確認用パスワード不一致
		if (!team2PasswordEditForm.getNewPassword().equals(team2PasswordEditForm.getConfirmPassword())) {
			System.out.println("変更失敗（パスワード不一致）");
			model.addAttribute("confirmPasswordError", "パスワードと確認用パスワードが一致しません");
			return "team2/users/team2_profile_password";
		}

		usersService.updatePassword(userId, team2PasswordEditForm.getNewPassword());
		redirectAttributes.addFlashAttribute("successMessage", "パスワードを変更しました");
		return "redirect:/team2/profile";
	}
	
	//退会画面表示
	@GetMapping("/team2/profile/delete")
	public String deleteForm(@ModelAttribute Team2ProfileDeleteForm team2DeleteForm) {
		return "team2/users/team2_profile_delete";
	}
	
	//退会
	@PostMapping("team2/profile/delete")
	public String delete(@ModelAttribute @Validated Team2ProfileDeleteForm team2ProfileDeleteForm, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		
		//バリデーション
		if (result.hasErrors()) {
			return "team2/users/team2_profile_delete";
		}
		
		int userId = (int) session.getAttribute("userId");
		
		
		//パスワード照合
		if (!usersService.checkPassword(userId, team2ProfileDeleteForm.getPassword())) {
			model.addAttribute("passwordError", "パスワードが正しくありません");
			return "team2/users/team2_profile_delete";
		}
		
		//退会処理
		usersService.delete(userId);
		session.invalidate();
		System.out.println("退会成功");
		redirectAttributes.addFlashAttribute("successMessage", "退会処理が完了しました");
		return "redirect:/team2/login";
	}

}
