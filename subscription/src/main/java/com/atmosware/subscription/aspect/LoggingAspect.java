package com.atmosware.subscription.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.atmosware.subscription.service.impl.SubscriptionServiceImpl.createSubscription(..))")
    public void createSubscriptionPointcut() {
    }

    @Pointcut("execution(* com.atmosware.subscription.service.impl.SubscriptionServiceImpl.cancelSubscription(..))")
    public void cancelSubscriptionPointcut() {
    }

    @Pointcut("execution(* com.atmosware.subscription.service.impl.SubscriptionServiceImpl.updateSubscription(..))")
    public void updateSubscriptionPointcut() {
    }

    @Before("createSubscriptionPointcut()")
    public void logCreateStart(JoinPoint joinPoint) {
        log.info("Yeni subscription oluşturuluyor.");
    }

    @Before("cancelSubscriptionPointcut()")
    public void logCancelStart(JoinPoint joinPoint) {
        log.info("Subscription iptal ediliyor");
    }

    @Before("updateSubscriptionPointcut()")
    public void logUpdateStart(JoinPoint joinPoint) {
        log.info("Subscription güncelleniyor.");
    }

    @AfterThrowing(pointcut = "createSubscriptionPointcut() || cancelSubscriptionPointcut() || updateSubscriptionPointcut()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        log.error("{} metodunda hata oluştu: {}", joinPoint.getSignature().getName(), ex.getMessage(), ex);
    }

}
