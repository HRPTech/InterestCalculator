package com.bestdeals.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.bestdeals.annotations.Logged;

@Aspect
@Component
public class LoggingAspect {

	@Around(value = "execution(public * * (..)) && @annotation(logger)", argNames = "logger")
	public Object logAround(ProceedingJoinPoint joinPoint, Logged logger) throws Throwable {
		String packageName = joinPoint.getSignature().getDeclaringTypeName();
		final Logger log = LoggerFactory.getLogger(packageName);

		StopWatch stopWatch = new StopWatch();
		if (logger.timed()) {
			stopWatch.start();
		}

		log.info("Enter " + joinPoint.getSignature().getName() + Arrays.toString(joinPoint.getArgs()));

		Object response = joinPoint.proceed(); // continue on the intercepted
		// method

		if (logger.timed()) {
			stopWatch.stop();
			log.info("Exit " + joinPoint.getSignature().getName() + "() response : " + response + " - (Timed :"
					+ stopWatch.getTotalTimeSeconds()+ "s)");
		} else {
			log.info("Exit " + joinPoint.getSignature().getName() + "() response : " + response);
		}
		return response;
	}

}
