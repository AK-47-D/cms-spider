package com.ak47.cms.cms.job

import com.ak47.cms.cms.service.CrawImageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*


@Component
class ImageCrawlerJob {

    @Autowired lateinit var crawImagesService: CrawImageService

    @Scheduled(cron = "0 0 10 */1 * ?")
    fun doSogouImageCrawJob() {
        println("开始执行定时任务 doSogouImageCrawJob： ${Date()}")
        crawImagesService.doSogouImageCrawJob()
    }

    @Scheduled(cron = "0 0 10 */1 * ?")
    fun doGankImageCrawJob() {
        println("开始执行定时任务 doGankImageCrawJob： ${Date()}")
        crawImagesService.doGankImageCrawJob()
    }

    @Scheduled(cron = "0 0 10 */1 * ?")
    fun doCrawHuaBanImagesJob() {
        println("开始执行定时任务 doCrawHuaBanImagesJob： ${Date()}")
        crawImagesService.doCrawHuaBanImages()
    }

}


