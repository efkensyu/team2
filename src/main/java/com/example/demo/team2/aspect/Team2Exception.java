package com.example.demo.team2.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Team2Exception {
	@Around("execution(* com.example.demo..*(..))")
	public Object exception(ProceedingJoinPoint jp) throws Throwable {
			try {
				return jp.proceed();
			}catch(Exception e) {
				throw e;	
			}
		}

}
