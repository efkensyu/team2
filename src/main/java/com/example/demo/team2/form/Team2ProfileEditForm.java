package com.example.demo.team2.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class Team2ProfileEditForm {
	private int userId;
	
	@NotBlank(message = "ユーザ名を入力してください")
	@Size(max = 50, message = "ユーザ名は50文字以内で入力してください")
	private String userName;
	
	@NotBlank(message = "ログインIDを入力してください")
	@Size(max = 50, message = "ログインIDは50文字以内で入力してください")
	private String loginId;
}
