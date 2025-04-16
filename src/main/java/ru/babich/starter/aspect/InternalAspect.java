package ru.babich.starter.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.babich.starter.properties.StarterLoggingProperties;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class InternalAspect {

    private final StarterLoggingProperties properties;

    @Before("@annotation(ru.babich.starter.annotation.InternalLogging)")
    public void logBeforeMethodExecution() {
        if (properties.isEnabled()) {
            logAtLevel("Начало выполнения метода с аннотацией @Loggable");
        }
    }

    @After("@annotation(ru.babich.starter.annotation.InternalLogging)")
    public void logAfterMethodExecution() {
        if (properties.isEnabled()) {
            logAtLevel("Завершение выполнения метода с аннотацией @Loggable");
        }
    }

    @Around("@annotation(ru.babich.starter.annotation.InternalLogging)")
    public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!properties.isEnabled()) {
            return joinPoint.proceed();
        }

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        long startTime = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - startTime;

                logAtLevel("<== {}.{}() - результат: {} (время выполнения: {} мс)",
                        className, methodName, result, executionTime);
            return result;
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            logAtLevel("<== {}.{}() - исключение: {} (время выполнения: {} мс)",
                    className, methodName, e.getMessage(), executionTime);
            throw e;
        }
    }

    private void logAtLevel(String message, Object... args) {
        if (!properties.isEnabled()) return;

        switch (properties.getLevel().toUpperCase()) {
            case "DEBUG" -> log.debug(message, args);
            case "INFO" -> log.info(message, args);
            case "WARN" -> log.warn(message, args);
            case "ERROR" -> log.error(message, args);
            default -> log.info(message, args);
        }
    }

}
