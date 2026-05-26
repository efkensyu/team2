package com.example.demo.team2.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class Team2LoginForm {
	@NotBlank(message = "ログインIDを入力してください。")
	private String loginId;

	@NotBlank(message = "パスワードを入力してください。")
	private String password;
}
