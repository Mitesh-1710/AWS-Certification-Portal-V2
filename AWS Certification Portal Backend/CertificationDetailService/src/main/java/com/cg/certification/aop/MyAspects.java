package com.cg.certification.aop;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspects {

	//initializing logger
	Logger logger = LoggerFactory.getLogger(MyAspects.class);
	
	//declaring pointcut for joinpoints				//spring does not provide logger for this particular class so avoiding runtime error i have excluded the class
	@Pointcut("execution(* com.cg.*.*.*.*(..)) &&  !target(org.springframework.web.filter.GenericFilterBean)" )
	public void myPointcut() {
		
	}
	
	@Around("myPointcut()")
	public Object logAround(ProceedingJoinPoint joinpoint) throws Throwable {
		
		String methodName = joinpoint.getSignature().getName();
		String type = joinpoint.getSignature().getDeclaringTypeName();
		String data = Arrays.toString(joinpoint.getArgs());
		
		logger.info("Request for " + type + "." + methodName + " with arguments[s] =  " + data ); 
		
		//defining clock time when method start
		Instant start = Instant.now();
		
		Object returnValue = joinpoint.proceed();
		
		//defining clock time when method ends
		Instant finish = Instant.now();
		
		//calculating time taken for method execution	
		long timeElapsed = Duration.between(start, finish).toMillis();
		
		//logging the method's passed information
		logger.info("Response for " + type + "." + methodName + " with value =  " + returnValue); 
		
		logger.info("Resolution for " + type + "." + methodName + " Execution Time taken =  " + timeElapsed + "ms"); 
		
		return returnValue;
	}
	
}

