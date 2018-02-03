package com.ak47.cms.cms.schedule

import com.ak47.cms.cms.dao.CronTriggerDao
import com.ak47.cms.cms.service.CrawTechArticleService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.config.ScheduledTaskRegistrar
import org.springframework.scheduling.support.CronTrigger
import org.springframework.util.StringUtils
import java.time.LocalDateTime


/**
 * Spring 实现 SchedulingConfigurer 接口完成动态定时任务（配合数据库动态执行）
 */

@Configuration
@EnableScheduling
class CustomScheduleConfig : SchedulingConfigurer {
    val CRAW_JIANSHU_TECH_ARTICLE_TASK_ID = 1

    val logger = LoggerFactory.getLogger(CustomScheduleConfig::class.java)

    @Autowired lateinit var CronTriggerDao: CronTriggerDao
    @Autowired lateinit var CrawTechArticleService: CrawTechArticleService


    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {

        taskRegistrar.addTriggerTask(
            //1 添加任务执行 Runnable
            {
                println("执行定时任务 doCrawJianShuTechArticle: " + LocalDateTime.now().toLocalTime())
                CrawTechArticleService.doCrawJianShuTechArticle()

            },
            //2 设置执行周期(Trigger)
            { triggerContext ->
                //3 从数据库获取执行周期
                val cron = CronTriggerDao.findByTaskId(CRAW_JIANSHU_TECH_ARTICLE_TASK_ID)
                //4 合法性校验
                if (StringUtils.isEmpty(cron)) {
                    // Omitted Code ..
                }
                //5 返回执行周期(Date)
                CronTrigger(cron?.cron).nextExecutionTime(triggerContext)
            }
        )

    }
}
