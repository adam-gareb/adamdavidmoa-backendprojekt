package se.yrgo.spring.advice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.*;

@Aspect
@Component
public class PerformanceTimingAdvice {

    private static final Logger logger = LogManager.getLogger(PerformanceTimingAdvice.class);

    @Around("execution(* se.yrgo.spring.services..*(..)) || execution(* se.yrgo.spring.dataaccess..*(..))")
    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.nanoTime();

        try {
            return joinPoint.proceed();
        } finally {
            long endTime = System.nanoTime();
            double timeTakenMs = (endTime - startTime) / 1_000_000.0;
            String methodName = joinPoint.getSignature().getName();
            String className = joinPoint.getTarget().getClass().getSimpleName();
            logger.info("{}.{} took {}ms", className, methodName, String.format("%.4f", timeTakenMs));
        }
    }
}