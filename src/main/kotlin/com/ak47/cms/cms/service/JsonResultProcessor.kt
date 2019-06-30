package com.ak47.cms.cms.service

import com.ak47.cms.cms.builder.CrawlerWebClient
import com.ak47.cms.cms.dto.ImageDTO
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray

object JsonResultProcessor {

    val crawlerWebClient = CrawlerWebClient.instanceCrawlerClient()

    fun getSogouImageUrlList(url: String): MutableList<ImageDTO> {
        return parseSogouImageUrlList(jsonstr = getUrlContent(url))
    }

    private fun parseSogouImageUrlList(jsonstr: String): MutableList<ImageDTO> {
        val imageResultList = mutableListOf<ImageDTO>()
        try {
            val obj = JSON.parse(jsonstr) as Map<*, *>
            val dataArray = obj.get("all_items") as JSONArray
            dataArray.forEach {
                val category = (it as Map<*, *>).get("title") as String
                val url = it.get("ori_pic_url") as String
                val imageResult = ImageDTO(category = category, url = url)
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


