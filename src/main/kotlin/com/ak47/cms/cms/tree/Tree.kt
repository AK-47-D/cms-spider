package com.ak47.cms.cms.tree

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(indexes = arrayOf(Index(name = "idx_category", columnList = "category")))
class Tree {

    @Id
    @Column(length = 100)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-uuid")
    @GenericGenerator(name = "custom-uuid", strategy = "com.ak47.cms.cms.tree.CustomUUIDGenerator")
    var code: String = "0"

    @Column(length = 100)
    var parentCode: String = "0"

    @Column(length = 200)
    var name = ""

    @Column(length = 100)
    var category = ""

    var gmtCreated = Date()
    var gmtModified = Date()
    var isDeleted = 0
}

/*
JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO.
TABLE：使用一个特定的数据库表格来保存主键。
SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
IDENTITY：主键由数据库自动生成（主要是自动增长型）
AUTO：主键由程序控制。
*/