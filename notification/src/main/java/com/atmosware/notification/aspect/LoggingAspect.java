package com.atmosware.notification.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.atmosware.mail.service.impl.MailServiceImpl.sendPaymentSuccedeedMail(..))")
    public void successMailPointcut() {
    }

    @Pointcut("execution(* com.atmosware.mail.service.impl.MailServiceImpl.sendPaymentFailedMail(..))")
    public void failedMailPointcut() {
    }

    @Pointcut("execution(* com.atmosware.mail.service.impl.MailServiceImpl.sendSubscriptionCreatedMail(..))")
    public void createdMailPointcut() {
    }

    @Pointcut("execution(* com.atmosware.mail.service.impl.MailServiceImpl.sendSubscriptionCancelleddMail(..))")
    public void cancelledMailPointcut() {
    }

    @Pointcut("execution(* com.atmosware.mail.service.impl.MailServiceImpl.sendSubscriptionUpdatedMail(..))")
    public void updatedMailPointcut() {
    }

    @Before("successMailPointcut()")
    public void logBeforeSuccessMail() {
        log.info("sendPaymentSuccedeedMail çağrıldı.");
    }

    @Before("failedMailPointcut()")
    public void logBeforeFailedMail() {
        log.info("sendPaymentFailedMail çağrıldı.");
    }

    @Before("createdMailPointcut()")
    public void logBeforeCreatedMail() {
        log.info("sendSubscriptionCreatedMail çağrıldı.");
    }

    @Before("cancelledMailPointcut()")
    public void logBeforeCancelledMail() {
        log.info("sendSubscriptionCancelleddMail çağrıldı.");
    }

    @Before("updatedMailPointcut()")
    public void logBeforeUpdatedMail() {
        log.info("sendSubscriptionUpdatedMail() çağrıldı.");
    }

    @AfterReturning("successMailPointcut()")
    public void logAfterSuccessMail() {
        log.info("Başarılı ödeme maili gönderildi.");
    }

    @AfterReturning("failedMailPointcut()")
    public void logAfterFailedMail() {
        log.info("Başarısız ödeme maili gönderildi.");
    }

    @AfterReturning("createdMailPointcut()")
    public void logAfterCreatedMail() {
        log.info("Abonelik oluşturma maili gönderildi.");
    }

    @AfterReturning("cancelledMailPointcut()")
    public void logAfterCancelledMail() {
        log.info("Abonelik iptal maili gönderildi.");
    }

    @AfterReturning("updatedMailPointcut()")
    public void logAfterUpdatedMail() {
        log.info("Abonelik güncelleme maili gönderildi.");
    }

    // ----- EXCEPTION (opsiyonel) -----
    @AfterThrowing(pointcut = "successMailPointcut() || failedMailPointcut() || createdMailPointcut() || cancelledMailPointcut() || updatedMailPointcut()", throwing = "ex")
    public void logMailException(JoinPoint joinPoint, Throwable ex) {
        log.error("{} metodunda mail gönderilirken hata oluştu: {}", joinPoint.getSignature().getName(), ex.getMessage(), ex);
    }
}
