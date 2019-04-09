package com.ak47.cms.cms.dao

import com.ak47.cms.cms.entity.JianShuTopic
import org.springframework.data.repository.CrudRepository

interface JianShuTopicRepository : CrudRepository<JianShuTopic, Long> {
    override fun findAll(): List<JianShuTopic>
}