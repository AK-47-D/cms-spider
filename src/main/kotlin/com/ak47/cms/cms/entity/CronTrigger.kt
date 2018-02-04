package com.ak47.cms.cms.entity

import java.util.*
import javax.persistence.*

/**
 * Created by Kor on 2018-02-04 01:42:11. author: 东海陈光剑
 */
@Entity
class CronTrigger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1
    var gmtCreate = Date()
    var gmtModify = Date()
    var isDeleted = 0

    @Column(unique = true)
    var taskId: Int = -1
    var cron: String = ""
}
