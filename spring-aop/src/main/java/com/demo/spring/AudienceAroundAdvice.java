package com.demo.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AudienceAroundAdvice {
	@Pointcut("execution(* com.demo.spring.Performer.perform(..))")
	public void pcut() {}
	
	public void takeSeat() {
		System.out.println("Audience take seats...");
	}

	public void switchOffMobile() {
		System.out.println("Audience Switched off mobiles...");
	}

	public void applaud() {
		System.out.println("Audience applauding with claps...");
	}

	@Around("pcut()")
	public Object invoke(ProceedingJoinPoint invocation) throws Throwable {
		System.out.println("Around Advice");
		takeSeat();
		switchOffMobile();
		Object obj = invocation.proceed();
		applaud();
		return obj;
	}
}
