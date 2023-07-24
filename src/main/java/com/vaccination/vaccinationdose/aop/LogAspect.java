package com.vaccination.vaccinationdose.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogAspect {

    Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Around(value = "@annotation(Log)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Long startTime = System.currentTimeMillis();
            Object obj = joinPoint.proceed();
            Long endTime = System.currentTimeMillis();

            log.info("Call to : {}" , joinPoint.getSignature().getName() + " took :{}" ,(endTime-startTime));

            return obj;

        }catch (Throwable ex){
            log.error("Failed to add logger: {}" ,ex.getCause());
            throw ex;
        }
    }
}
