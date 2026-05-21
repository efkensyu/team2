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
		log.info("メソッド開始：{} 引数=[{}]",jp.getSignature(),jp.getArgs());
		}
	
	@AfterReturning("execution(* com.example.demo..*(..))")
	public void returningLog(JoinPoint jp) {
		log.info("メソッド正常終了：{} 引数=[{}]",jp.getSignature(),jp.getArgs());
	}
	
	@AfterThrowing(pointcut = "execution(* com.example.demo..*(..))", throwing = "e")
	public void throwingLog(JoinPoint jp, Exception e) {
		log.error("エラー発生：{} 引数=[{}] メッセージ：{}",jp.getSignature(),jp.getArgs(), e.getMessage());//Arrays.toString（jp.getArgs()）
		
	}
}
