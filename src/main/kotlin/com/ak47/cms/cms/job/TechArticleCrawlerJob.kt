package com.ak47.cms.cms.job

import com.ak47.cms.cms.service.CrawTechArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*


@Component
class TechArticleCrawlerJob {

    @Autowired
    lateinit var crawTechArticleService: CrawTechArticleService

    @Scheduled(cron = "0 0 10 */1 * ?")
    fun doCrawJob() {
        println("开始执行定时任务 doSogouImageCrawJob： ${Date()}")
        crawTechArticleService.doCrawJianShuTechArticle()
    }

}


