package com.ak47.cms.cms.service

import com.ak47.cms.cms.api.CrawlerWebClient
import com.ak47.cms.cms.api.ImageSearchApiBuilder
import com.ak47.cms.cms.dao.ImageRepository
import com.ak47.cms.cms.entity.Image
import org.jsoup.Jsoup
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

@Service
class CrawImageService {
    val logger = LoggerFactory.getLogger(CrawImageService::class.java)

    val crawlerWebClient = CrawlerWebClient.instanceCrawlerClient()

    @Autowired
    lateinit var imageRepository: ImageRepository

    fun doSogouImageCrawJob() {

        for (i in 1..10) {
            saveSogouImage(currentPage = i, pageSize = 20)
        }
    }


    fun doCrawHuaBanImages() {
        val boardsUrls = getBoards()
        boardsUrls.forEach {
            saveHuaBanImages(it)
        }
    }

    private fun getBoards(): MutableSet<String> {
        val boardsUrl = "https://huaban.com/boards/favorite/beauty/"
        val boardsUrls = mutableSetOf<String>()

        try {
            val boardsPageHtml = crawlerWebClient.getPage(boardsUrl).asXml()
            val document = Jsoup.parse(boardsPageHtml)
            println("document = ${document}")

            document.getElementsByClass("Board wfc wft").forEach {
                val data_id = it.attr("data-id")
                boardsUrls.add("http://huaban.com/boards/${data_id}")
            }
        } catch (e: Exception) {
        }

        println("boardsUrls = ${boardsUrls}")
        return boardsUrls
    }

    private fun saveHuaBanImages(beautyUrl: String) {

        val beautyPageHtml = crawlerWebClient.getPage(beautyUrl).asXml()
        val document = Jsoup.parse(beautyPageHtml)

        document.getElementById("waterfall").children().forEach {
            var url = "http:" + it.child(2).child(1).attr("src")
            url = url.replace("_fw236", "_fw1000")
            println("花瓣 url = ${url}")
            if (imageRepository.countByUrl(url) == 0) {
                val image = Image()
                image.category = "花瓣 ${SimpleDateFormat("yyyy-MM-dd").format(Date())}"
                image.url = url
                image.sourceType = 2
                image.imageBlob = getByteArray(url)
                imageRepository.save(image)
            }
        }
    }


    private fun saveSogouImage(currentPage: Int, pageSize: Int) {
        val api = ImageSearchApiBuilder.build(currentPage, pageSize)
        JsonResultProcessor.getSogouImageUrlList(api).forEach {
            val category = it.category
            val url = it.url
            if (imageRepository.countByUrl(url) == 0) {
                val image = Image()
                image.category = category
                image.url = url
                image.sourceType = 0
                image.imageBlob = getByteArray(url)
                try {
                    imageRepository.save(image)
                } catch (e: Exception) {
                }
            }
        }
    }

    private fun getByteArray(url: String): ByteArray {
        val urlObj = URL(url)
        return urlObj.readBytes()
    }
}
