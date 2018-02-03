package com.ak47.cms.cms.dao

import com.ak47.cms.cms.entity.CronTrigger
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * Created by Kor on 2018-02-04 01:42:11. author: 东海陈光剑
 */
interface CronTriggerDao : JpaRepository<CronTrigger, Long> {

    @Query("""
        select a from #{#entityName} a where a.taskId = :taskId
    """)
    fun findByTaskId(@Param("taskId") taskId: Int): CronTrigger?
}
