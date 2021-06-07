package gg.jominsubyungsin.admin.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class TimeTraceAop {

    @Around("execution(* gg.jominsubyungsin.admin.controller..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{//메서드 호출마다 실행
        long start = System.currentTimeMillis();

        //어떤 매서드를 콜 하는지 알 수 있음
        log.info("*****❔START : " + joinPoint.toString());

        try{
            Object result = joinPoint.proceed();//다음 메서드로 진행이 됨

            return result;
        } finally {
            long finish = System.currentTimeMillis();
            long time = finish - start;
            log.info("*****❓END : " + joinPoint.toString() + " " + time + "ms");
        }
    }
}
