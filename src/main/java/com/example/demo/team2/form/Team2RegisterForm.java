package com.example.demo.team2.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class Team2RegisterForm {
	@NotBlank(message = "ログインIDを入力してください")
	@Size(max = 50, message = "ログインIDは50文字以内で入力してください")
	private String loginId;
	
	@NotBlank(message = "パスワードを入力してください")
	@Size(max = 50, message = "パスワードは8文字以上20文字以内で入力してください")
	private String password;
	
	@NotBlank(message = "確認用パスワードを入力してください")
	private String confirmPassword;
	
	@NotBlank(message = "ユーザ名を入力してください")
	@Size(max = 20, message = "ユーザ名は20文字以内で入力してください")
	private String userName;
}
