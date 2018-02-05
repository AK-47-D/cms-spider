package com.ak47.cms.cms.monitor

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.metrics.CounterService
import org.springframework.boot.actuate.metrics.GaugeService
import org.springframework.stereotype.Component

@Aspect
@Component
class ServiceMonitor {
    @Autowired
    lateinit var counterService: CounterService;

    @Before("execution(* com.ak47.cms.cms.service.*.*(..))")
    fun countServiceInvoke(joinPoint: JoinPoint) {
        counterService.increment("${joinPoint.getSignature()}");
    }


    @Autowired
    lateinit var gaugeService: GaugeService;

    @Around("execution(* com.ak47.cms.cms.service.*.*(..))")
    fun latencyService(pjp: ProceedingJoinPoint) {
        val start = System.currentTimeMillis();
        pjp.proceed();
        val end = System.currentTimeMillis();
        gaugeService.submit(pjp.getSignature().toString(), (end - start).toDouble());
    }


}
