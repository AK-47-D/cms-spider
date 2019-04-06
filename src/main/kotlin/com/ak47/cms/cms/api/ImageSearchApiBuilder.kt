package com.ak47.cms.cms.api

object ImageSearchApiBuilder {
    fun build(word: String, page: Int): String {
        return "https://pic.sogou.com/pics?query=${word}&mode=1&reqType=ajax&start=${page}"
    }
}
