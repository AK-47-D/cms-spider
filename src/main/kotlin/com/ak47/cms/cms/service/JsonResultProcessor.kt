package com.ak47.cms.cms.service

import com.ak47.cms.cms.api.CrawlerWebClient
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.ak47.cms.cms.dto.ImageCategoryAndUrl

object JsonResultProcessor {

    val crawlerWebClient = CrawlerWebClient.instanceCrawlerClient()


    fun getSogouImageCategoryAndUrlList(url: String): MutableList<ImageCategoryAndUrl> {
        return parseSogouImageCategoryAndUrlList(jsonstr = getUrlContent(url))
    }

    fun getGankImageUrls(url: String): MutableList<String> {
        return parseGankImageUrls(jsonstr = getUrlContent(url))
    }

    fun parseSogouImageCategoryAndUrlList(jsonstr: String): MutableList<ImageCategoryAndUrl> {
        val imageResultList = mutableListOf<ImageCategoryAndUrl>()
        try {
            val obj = JSON.parse(jsonstr) as Map<*, *>
            val dataArray = obj.get("items") as JSONArray
            dataArray.forEach {
                val category = (it as Map<*, *>).get("title") as String
                val url = it.get("ori_pic_url") as String
                if (passFilter(url)) {
                    val imageResult = ImageCategoryAndUrl(category = category, url = url)
                    imageResultList.add(imageResult)
                }
            }

        } catch (ex: Exception) {

        }
        return imageResultList
    }

    fun getUrlContent(url: String): String {
        return crawlerWebClient.getJson(url)
    }

    fun passFilter(imgUrl: String): Boolean {
        return imgUrl.endsWith(".jpg")
                && !imgUrl.contains("baidu.com/")
                && !imgUrl.contains("126.net")
                && !imgUrl.contains("pconline.com")
                && !imgUrl.contains("nipic.com")
                && !imgUrl.contains("zol.com")
    }

    fun parseGankImageUrls(jsonstr: String): MutableList<String> {
        val urls = mutableListOf<String>()
        try {
            val obj = JSON.parse(jsonstr) as Map<*, *>
            val dataArray = obj.get("results") as JSONArray
            dataArray.forEach {
                val url = (it as Map<*, *>).get("url") as String
                urls.add(url)
            }
        } catch (ex: Exception) {
        }
        return urls
    }


}


