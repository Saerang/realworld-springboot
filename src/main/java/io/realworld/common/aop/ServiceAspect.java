package io.realworld.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Aspect for logging execution of service and repository Spring components.
 * @author Ramesh Fadatare
 *
 */
@Slf4j
@Aspect
@Component
public class ServiceAspect {

    @Pointcut("execution(* io.realworld..*.app.*Service.*(..) )")
    public void serviceAdvice() {
    }

    @Before("serviceAdvice()")
    public void requestLogging(JoinPoint joinPoint) {
        log.debug("[" + joinPoint.getSignature().toShortString() + "] -------------------------- Service START --------------------------");
        log.debug("[" + joinPoint.getSignature().toShortString() + "] Service Parameters: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "serviceAdvice()", returning = "result")
    public void requestLogging(JoinPoint joinPoint, Object result) {
        log.debug("[" + joinPoint.getSignature().toShortString() + "] -------------------------- Service FINISH --------------------------");
    }

    @AfterReturning(pointcut = "serviceAdvice()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        if (result instanceof Collection) {
            result = ((Collection) result).stream().limit(100).collect(Collectors.toList());
        }

        log.debug("[" + joinPoint.getSignature().toShortString() + "] Service Returned: " + result);
        log.debug("[" + joinPoint.getSignature().toShortString() + "] -------------------------- Service FINISH --------------------------");
    }
}
