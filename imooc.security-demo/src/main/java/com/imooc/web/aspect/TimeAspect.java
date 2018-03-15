package com.imooc.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {
//	@Before
//	@After
//	@AfterThrowing
	@Around("execution(* com.imooc.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("time aspect start");
		
		long start = new Date().getTime();
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			System.out.println("arg is " +arg);
		}
		Object proceed = pjp.proceed();
		
		System.out.println("time aspect 耗时"+ (new Date().getTime()-start));
		
		System.out.println("time aspect end");
		return proceed;
	}
}
