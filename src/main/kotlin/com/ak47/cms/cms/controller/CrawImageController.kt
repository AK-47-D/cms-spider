package com.ak47.cms.cms.controller

import com.ak47.cms.cms.service.CrawImageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by jack on 2017/7/22.
 */

@RestController
class CrawImageController {

    @Autowired
    lateinit var crawImageService: CrawImageService

    @GetMapping("doSogouImageCrawJob")
    fun doSogouImageCrawJob(): String {
        Thread {
            crawImageService.doSogouImageCrawJob()
        }.start()
        return "doSogouImageCrawJob JOB Started"
    }


    @GetMapping("doHuaBanImageCrawJob")
    fun doHuaBanImageCrawJob(): String {
        Thread {
            crawImageService.doCrawHuaBanImages()
        }.start()
        return "doCrawHuaBanImages JOB Started"
    }

}
