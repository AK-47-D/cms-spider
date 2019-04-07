package com.ak47.cms.cms.controller

import com.ak47.cms.cms.job.BatchUpdateJob
import com.ak47.cms.cms.service.CrawImageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by jack on 2017/7/22.
 */

@Controller
class CrawController {

    @Autowired
    lateinit var crawImageService: CrawImageService
    @Autowired
    lateinit var batchUpdateJob: BatchUpdateJob

    @RequestMapping(value = "doSogouImageCrawJob", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun doSogouImageCrawJob(): String {
        Thread {
            crawImageService.doSogouImageCrawJob()

        }.start()

        return "doSogouImageCrawJob JOB Started"
    }

    @RequestMapping(value = "doGankImageCrawJob", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun doGankImageCrawJob(): String {
        Thread {
            crawImageService.doGankImageCrawJob()

        }.start()

        return "doSogouImageCrawJob JOB Started"
    }

    @RequestMapping(value = "doHuaBanImageCrawJob", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun doHuaBanImageCrawJob(): String {
        Thread {
            crawImageService.doCrawHuaBanImages()

        }.start()

        return "doCrawHuaBanImages JOB Started"
    }

    @RequestMapping(value = "doBatchUpdateJob", method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun BatchUpdateJob(): String {

        batchUpdateJob.job()

        return "BatchUpdateJob Started"
    }


}
