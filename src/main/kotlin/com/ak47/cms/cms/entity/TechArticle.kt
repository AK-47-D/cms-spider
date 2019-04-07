package com.ak47.cms.cms.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(indexes = arrayOf(
        Index(name = "idx_url", unique = true, columnList = "url")))
open class TechArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1

    var url = "URL"
    var title = "Kotlin 使用 Spring WebFlux 实现响应式编程"

    @Lob
    var simpleContent = "文章摘要"
    var category = "分类"

    var gmtCreate = Date()
    var gmtModified = Date()

}