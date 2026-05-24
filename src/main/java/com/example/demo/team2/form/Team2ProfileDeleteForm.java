package com.example.demo.team2.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class Team2ProfileDeleteForm {
	@NotBlank(message = "現在のパスワードを入力してください")
	private String password;
}
