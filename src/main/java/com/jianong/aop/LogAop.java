package com.jianong.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.jianong.util.Logger;

@Aspect
@Component
public class LogAop {

	private static final Logger LOGGER = Logger.getLogger(LogAop.class);

	@Around("execution(public * com.jianong.controller.*.*(..))")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		joinPoint.proceed();
		long costTime = System.currentTimeMillis() - startTime;

		String methodName = joinPoint.getSignature().getName();
		// 记录执行请求耗时
		LOGGER.info(methodName + " finished ! Cost : " + costTime + " ms.");
	}

}
