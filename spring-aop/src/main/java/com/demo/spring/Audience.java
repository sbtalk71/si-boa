package com.demo.spring;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class Audience {

	@Pointcut("execution(* com.demo.spring.Performer.perform(..))")
	public void pcut() {}
	
	@Before("pcut()")
	public void takeSeat() {
		System.out.println("Audience take seats...");
	}
	
	@Before("pcut()")
	public void switchOffMobile() {
		System.out.println("Audience Switched off mobiles...");
	}
	
	@AfterReturning("pcut()")
	public void applaud() {
		System.out.println("Audience applauding with claps...");
	}
}
