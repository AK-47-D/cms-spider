package com.ak47.cms.cms.service

import com.ak47.cms.cms.builder.CrawlerWebClient
import com.ak47.cms.cms.dao.JianShuTopicRepository
import com.ak47.cms.cms.dao.TechArticleRepository
import com.ak47.cms.cms.entity.TechArticle
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import org.jsoup.Jsoup
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CrawTechArticleService {
    val logger = LoggerFactory.getLogger(CrawTechArticleService::class.java)
    val crawlerWebClient = CrawlerWebClient.instanceCrawlerClient()
    @Autowired
    lateinit var TechArticleRepository: TechArticleRepository
    @Autowired
    lateinit var JianShuTopicRepository: JianShuTopicRepository


    fun doCrawJianShuTechArticle() {
        launch(CommonPool) {
            val topics = JianShuTopicRepository.findAll()

            topics.forEach {
                for (page in 1..20) {
                    crawJianShuArticles(page, it.url)
                }
            }
        }

    }

    private fun doSaveTechArticle(url: String, title: String, simpleContent: String, category: String) {
        val TechArticle = TechArticle()
        TechArticle.url = url
        TechArticle.title = title
        TechArticle.simpleContent = simpleContent
        TechArticle.category = category

        try {
            TechArticleRepository.save(TechArticle)
        } catch (e: Exception) {
        }
    }


    fun crawJianShuArticles(page: Int, topics: String) {
        val CRAW_API = "${topics}?order_by=added_at&page=${page}"
        val html = crawlerWebClient.getPage(CRAW_API).asXml()
        val document = Jsoup.parse(html)

        document.getElementsByClass("content").forEach {

            val url = "http://www.jianshu.com" + it.child(0).attr("href")
            val title = it.child(0).html()
            val simpleContent = it.child(1).html()
            val showContent = getJianShuShowContent(url)
            if (TechArticleRepository.countByUrl(url) == 0) {
                doSaveTechArticle(
                        url = url,
                        title = title,
                        simpleContent = simpleContent,
                        category = "简书"
                )
            }
        }


    }

    private fun getJianShuShowContent(url: String): String {
        val html = crawlerWebClient.getPage(url).asXml()
        val document = Jsoup.parse(html)
        return document.getElementsByClass("article")[0].html()
    }

}