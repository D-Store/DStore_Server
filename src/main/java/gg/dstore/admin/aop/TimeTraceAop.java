package gg.dstore.admin.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class TimeTraceAop {

    @Around("execution(* gg.dstore.admin.controller..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();

        log.info("*****❔START : " + joinPoint.toString());

        try{
            Object result = joinPoint.proceed();

            return result;
        } finally {
            long finish = System.currentTimeMillis();
            long time = finish - start;
            log.info("*****❓END : " + joinPoint.toString() + " " + time + "ms");
        }
    }
}
