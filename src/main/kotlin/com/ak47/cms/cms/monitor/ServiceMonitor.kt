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

    @Before("execution(* com.ak47.cms.cms.controller..*.*(..))")
    fun countServiceInvoke(joinPoint: JoinPoint) {
        counterService.increment("${joinPoint.getSignature()}");
    }


    @Autowired
    lateinit var gaugeService: GaugeService;

    @Around("execution(* com.ak47.cms.cms.service..*.*(..))")
    fun latencyService(pjp: ProceedingJoinPoint) {
        val start = System.currentTimeMillis()
        pjp.proceed()
        val end = System.currentTimeMillis()
        gaugeService.submit(pjp.getSignature().toString(), (end - start).toDouble())
    }


}


/**
execution（）
表达式的主体；
第一个”*“符号
表示返回值的类型任意；
com.sample.service.impl	AOP所切的服务的包名，即，我们的业务部分
包名后面的”..“	表示当前包及子包
第二个”*“	表示类名，*即所有类。此处可以自定义，下文有举例
.*(..)	表示任何方法名，括号表示参数，两个点表示任何参数类型
 */