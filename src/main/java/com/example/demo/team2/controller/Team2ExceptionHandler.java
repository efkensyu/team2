package com.example.demo.team2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Team2ExceptionHandler {

	private static final Logger logger =
			LoggerFactory.getLogger(Team2ExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) {

		logger.error("予期しないエラーが発生しました", e);

		return "team2/kyoutu/team2_error";
	}
}