package com.ak47.cms.cms.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(indexes = arrayOf(Index(name = "idx_url", unique = true, columnList = "url")))
class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1
    @Version
    var version = 0

    @Column(length = 1000)
    var category = ""

    @Column(length = 200)
    var url = ""

    var isFavorite = 0
    var loveCount = 0

    var gmtCreated = Date()
    var gmtModified = Date()
    var isDeleted = 0  //1 Yes 0 No
    var deletedDate = Date()

    @Lob
    var imageBlob = byteArrayOf()
    var sourceType = 0

}
