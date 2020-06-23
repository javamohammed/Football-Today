package com.mido.football.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CRMLoggingAspect {

	private Logger MyLogger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.web.customer.tracker.controller.*.*(..) )")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.web.customer.tracker.dao.*.*(..) )")
	private void forDaoPackage() {}
	
	@Pointcut("execution(* com.web.customer.tracker.service.*.*(..) )")
	private void forServicePackage() {}
	
	@Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
	private void forAppFlow() {}
	
	@Before("forAppFlow()")
	public void forAppFlowBefore(JoinPoint joinPoint) {
		
		String method = joinPoint.getSignature().toShortString();
		MyLogger.info("===> "+method);
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			MyLogger.info("==> "+arg);
		}
	}
	
	@AfterReturning(pointcut = "forAppFlow()", returning = "result")
	public void getResultAfterRunning(JoinPoint joinPoint, Object result) {
	
		String method = joinPoint.getSignature().toShortString();
		MyLogger.info("===> "+method);
		MyLogger.info("=getResultAfterRunning==> "+result);
		
	}
}
