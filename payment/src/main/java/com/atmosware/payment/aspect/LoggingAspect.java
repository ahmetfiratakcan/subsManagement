package com.atmosware.payment.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.atmosware.payment.service.impl.PaymentServiceImpl.createSubscription(..))")
    public void createSubPointcut() {
    }

    @Pointcut("execution(* com.atmosware.payment.service.impl.PaymentServiceImpl.cancelSubscription(..))")
    public void cancelSubPointcut() {
    }

    @Pointcut("execution(* com.atmosware.payment.service.impl.PaymentServiceImpl.updateSubscription(..))")
    public void updateSubPointcut() {
    }

    @Pointcut("execution(* com.atmosware.payment.service.impl.PaymentServiceImpl.processSubscriptionPayment(..))")
    public void processPaymentPointcut() {
    }

    @Before("createSubPointcut()")
    public void logBeforeCreate(JoinPoint joinPoint) {
        log.info("Yeni subscription oluşturuluyor.");
    }

    @Before("cancelSubPointcut()")
    public void logBeforeCancel(JoinPoint joinPoint) {
        log.info("Subscription iptal ediliyor.");
    }

    @Before("updateSubPointcut()")
    public void logBeforeUpdate(JoinPoint joinPoint) {
        log.info("Subscription güncelleniyor.");
    }

    @Before("processPaymentPointcut()")
    public void logBeforeProcess(JoinPoint joinPoint) {
        log.info("Ödeme yapılıyor.");
    }

    @AfterReturning("processPaymentPointcut()")
    public void logProcessDone() {
        log.info("Ödeme başarıyla alındı.");
    }

    @AfterThrowing(pointcut = "createSubPointcut() || cancelSubPointcut() || updateSubPointcut() || processPaymentPointcut()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        log.error("{} metodunda hata oluştu: {}", joinPoint.getSignature().getName(), ex.getMessage(), ex);
    }
}
