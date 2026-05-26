package com.example.demo.team2.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class Team2ProfileEditForm {
	private int userId;
	
	@NotBlank(message = "ユーザ名を入力してください")
	@Size(max = 20, message = "ユーザ名は20文字以内で入力してください")
	private String userName;
	
	@NotBlank(message = "ログインIDを入力してください")
	@Size(max = 20, message = "ログインIDは20文字以内で入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "ログインIDは半角英数字・._-で入力してください")
	private String loginId;
}
