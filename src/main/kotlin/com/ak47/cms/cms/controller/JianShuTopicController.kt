package com.ak47.cms.cms.controller

import com.ak47.cms.cms.dao.JianShuTopicRepository
import com.ak47.cms.cms.entity.JianShuTopic
import com.ak47.cms.cms.result.Result
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class JianShuTopicController {
    val logger = LoggerFactory.getLogger(JianShuTopicController::class.java)

    @Autowired
    lateinit var JianShuTopicRepository: JianShuTopicRepository


    @ResponseBody
    @PostMapping("saveJianShuTopic")
    fun save(JianShuTopic: JianShuTopic): Result<Boolean> {
        try {
            JianShuTopicRepository.save(JianShuTopic)
            return Result(true, "", "", true)
        } catch (e: Exception) {
            logger.error("save:", e)
            return Result(false, e.message, "", false)
        }
    }

    @ResponseBody
    @PostMapping("deleteJianShuTopic")
    fun deleteById(id: Long): Result<Boolean> {

        try {
            JianShuTopicRepository.deleteById(id)
            return Result(true, "", "", true)
        } catch (e: Exception) {
            logger.error("save:", e)
            return Result(false, e.message, "", false)
        }

    }

    @ResponseBody
    @GetMapping("listJianShuTopic")
    fun list(): Result<List<JianShuTopic>> {
        try {
            val data = JianShuTopicRepository.findAll()
            return Result(data, "", "", true)
        } catch (e: Exception) {
            logger.error("save:", e)
            return Result(listOf(), e.message, "", false)
        }

    }

}