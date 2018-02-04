package com.ak47.cms.cms.schedule

import com.ak47.cms.cms.dao.CronTriggerDao
import com.ak47.cms.cms.service.CrawImageService
import com.ak47.cms.cms.service.CrawTechArticleService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.config.ScheduledTaskRegistrar
import org.springframework.scheduling.support.CronTrigger
import org.springframework.util.StringUtils
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors


/**
 * Spring Boot 中实现 SchedulingConfigurer 接口完成动态定时任务（配合数据库动态执行）
 */

@Configuration
class CustomScheduleConfig : SchedulingConfigurer {
    val DEFAULT_CRON = "0 0/1 * * * ? "
    val CRAW_JIANSHU_TECH_ARTICLE_TASK_ID = 1
    val GANK_IMAGE_CRAW_TASK_ID = 2

    val logger = LoggerFactory.getLogger(CustomScheduleConfig::class.java)

    @Autowired lateinit var cronTriggerDao: CronTriggerDao

    @Autowired lateinit var crawTechArticleService: CrawTechArticleService
    @Autowired lateinit var crawImagesService: CrawImageService

    val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {

        //设定一个长度10的定时任务线程池
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(10))

        taskRegistrar.addTriggerTask(
                //1 添加任务执行 Runnable
                {
                    println("${df.format(Date())} 执行任务 1，线程： ${Thread.currentThread().name}")
                    crawTechArticleService.doCrawJianShuTechArticle()
                },
                //2 设置执行周期(Trigger)
                { triggerContext ->
                    //3 从数据库获取执行周期
                    val cron = cronTriggerDao.findByTaskId(CRAW_JIANSHU_TECH_ARTICLE_TASK_ID)
                    // 4 合法性校验
                    var cronExpression: String? = DEFAULT_CRON
                    if (!StringUtils.isEmpty(cron?.cron)) {
                        cronExpression = cron?.cron
                    }
                    //5 返回执行周期(Date)
                    CronTrigger(cronExpression).nextExecutionTime(triggerContext)
                }
        )

        taskRegistrar.addTriggerTask( // 添加任务2
                {
                    println("${df.format(Date())} 执行任务 2，线程： ${Thread.currentThread().name}")
                    crawImagesService.doGankImageCrawJob()
                },
                { triggerContext ->
                    val cron = cronTriggerDao.findByTaskId(GANK_IMAGE_CRAW_TASK_ID)
                    var cronExpression: String? = DEFAULT_CRON
                    if (!StringUtils.isEmpty(cron?.cron)) {
                        cronExpression = cron?.cron
                    }
                    CronTrigger(cronExpression).nextExecutionTime(triggerContext)
                }
        )

    }
}
