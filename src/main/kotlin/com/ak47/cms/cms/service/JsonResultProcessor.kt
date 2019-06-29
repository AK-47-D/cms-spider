package com.ak47.cms.cms.service

import com.ak47.cms.cms.api.CrawlerWebClient
import com.ak47.cms.cms.dto.ImageCategoryAndUrl
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray

object JsonResultProcessor {

    val crawlerWebClient = CrawlerWebClient.instanceCrawlerClient()

    fun getSogouImageUrlList(url: String): MutableList<ImageCategoryAndUrl> {
        return parseSogouImageUrlList(jsonstr = getUrlContent(url))
    }

    private fun parseSogouImageUrlList(jsonstr: String): MutableList<ImageCategoryAndUrl> {
        val imageResultList = mutableListOf<ImageCategoryAndUrl>()
        try {
            val obj = JSON.parse(jsonstr) as Map<*, *>
            val dataArray = obj.get("all_items") as JSONArray
            dataArray.forEach {
                val category = (it as Map<*, *>).get("title") as String
                val url = it.get("ori_pic_url") as String
                val imageResult = ImageCategoryAndUrl(category = category, url = url)
                imageResultList.add(imageResult)
            }

        } catch (ex: Exception) {
        }
        return imageResultList
    }

    fun getUrlContent(url: String): String {
        return crawlerWebClient.getJson(url)
    }

}


