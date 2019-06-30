package com.ak47.cms.cms

import com.ak47.cms.cms.dao.JianShuTopicRepository
import com.ak47.cms.cms.dao.TreeRepository
import com.ak47.cms.cms.entity.JianShuTopic
import com.ak47.cms.cms.tree.Tree
import com.ak47.cms.cms.tree.TreeCategory
import com.ak47.cms.cms.tree.TreeUtil
import com.ak47.cms.cms.vo.TreeVO
import com.alibaba.fastjson.JSON
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.io.File


@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
class CmsApplication

val logger = LoggerFactory.getLogger(CmsApplication::class.java)

fun main(args: Array<String>) {
    runApplication<CmsApplication>(*args)
}

@Component
@Order(value = Ordered.LOWEST_PRECEDENCE)
class InitRunner : CommandLineRunner {
    @Autowired
    lateinit var JianShuTopicRepository: JianShuTopicRepository
    @Autowired
    lateinit var treeRepository: TreeRepository


    override fun run(vararg args: String) {
        initTopics()
        initTreeCountry()
    }

    private fun initTreeCountry() {
        logger.info("initTreeCountry")
        // clean
        // treeRepository.deleteAll()

        // read init file data
        val cp = Thread.currentThread().contextClassLoader.getResource("").path;
        val filePath = "$cp/tree/country.js"
        val initCountryJson = File(filePath).readText()

        val nodes = JSON.parseArray(initCountryJson, TreeVO::class.java)

        nodes.forEach {
            val node = it

            TreeUtil.visitTree(node) {
                val t = Tree()
                t.code = it.code
                t.name = it.name
                t.parentCode = it.parentCode
                t.category = TreeCategory.COUNTRY.name
                try {
                    treeRepository.save(t)
                } catch (e: Exception) {
                    logger.error("t:{}", JSON.toJSONString(t))
                    logger.error("treeRepository.save(t): ", e)
                }
            }
        }


    }

    private fun initTopics() {
        logger.info("initTopics")

        topicUrls.forEach {
            try {
                val jianshuTopic = JianShuTopic()
                jianshuTopic.url = it
                JianShuTopicRepository.save(jianshuTopic)
            } catch (e: Exception) {

            }
        }
    }
}

val topicUrls = arrayOf(
        "http://www.jianshu.com/c/498ebcfd27ad",
        "http://www.jianshu.com/c/c3fe8e7aeb09",
        "http://www.jianshu.com/c/61314ad84456",
        "http://www.jianshu.com/c/f0cf6eae1754",
        "http://www.jianshu.com/c/98aaef9f5d2f",
        "http://www.jianshu.com/c/1d2b61da81ad",
        "http://www.jianshu.com/c/2e2ddd6ba967",
        "http://www.jianshu.com/c/ef7836bf3e22",
        "http://www.jianshu.com/c/38d96caffb2f",
        "http://www.jianshu.com/c/04cb7410c597")