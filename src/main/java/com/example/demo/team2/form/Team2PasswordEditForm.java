package com.example.demo.team2.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class Team2PasswordEditForm {
	@NotBlank(message = "現在のパスワードを入力してください")
	private String currentPassword;
	
	@NotBlank(message = "パスワードを入力してください")
	@Size(min = 8, max = 20, message = "パスワードは8文字以上20文字以内で入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=]+$", message = "パスワードは半角英数字・記号で入力してください")
	private String newPassword;
	
	@NotBlank(message = "確認用パスワードを入力してください")
	private String confirmPassword;
	
}