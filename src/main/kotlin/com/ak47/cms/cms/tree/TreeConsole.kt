package com.ak47.cms.cms.tree

import java.util.*
import javax.persistence.*

@Entity
@Table(indexes = arrayOf(Index(name = "idx_category", unique = true, columnList = "category")))
class TreeConsole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @Column(length = 100)
    var category = ""

    @Lob
    var data = "{}"

    var gmtCreated = Date()
    var gmtModified = Date()
    var isDeleted = 0
}
