package com.ak47.cms.cms.builder

object ImageSearchApiBuilder {
    fun build(currentPage: Int, pageSize: Int): String {
        return "https://pic.sogou.com/pics/channel/getAllRecomPicByTag.jsp?category=%E7%BE%8E%E5%A5%B3&tag=%E6%B8%85%E7%BA%AF&start=${currentPage * pageSize}&len=$pageSize"
    }
}