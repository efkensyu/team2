package com.example.demo.team2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class Team2Logback {
	@Before("execution(* com.example.demo..*.*(..))")
	public void beforeLog(JoinPoint jp) {
		log.info("メソッド開始：{}",jp.getSignature());
		log.info("引数=[{}]",jp.getArgs());
		}
	
	@AfterReturning("execution(* com.example.demo..*(..))")
	public void returningLog(JoinPoint jp) {
		log.info("メソッド正常終了：{}",jp.getSignature());
		log.info("引数=[{}]",jp.getArgs());
	}
	
	@AfterThrowing("execution(* com.example.demo..*(..))")
	public void throwingLog(JoinPoint jp) {
		log.error("エラー発生：{}",jp.getSignature());//Arrays.toString（jp.getArgs()）
		log.info("引数=[{}]",jp.getArgs());
	}
}
