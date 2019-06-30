package com.ak47.cms.cms.vo

class TreeVO {
    var code: String = ""
    var parentCode: String = ""
    var name: String = ""
    var category: String = ""
    var children: List<TreeVO> = mutableListOf()
}