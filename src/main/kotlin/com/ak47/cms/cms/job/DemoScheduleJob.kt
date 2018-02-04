package com.ak47.cms.cms.job

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

//@Component
class DemoScheduleJob {

    @Scheduled(cron = "0/10 * * * * ?") // 每隔1分钟执行1次
    fun job1() {
        println("执行任务job1： ${Date()}")
    }

    @Scheduled(fixedDelay = 3000) // ms
    fun job2() {
        println("执行任务job2： ${Date()}")
    }

    @Scheduled(fixedRate = 5000) // ms
    fun job3() {
        println("执行任务job3： ${Date()}")
    }

}