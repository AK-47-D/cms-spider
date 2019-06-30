package com.ak47.cms.cms

import com.ak47.cms.cms.dao.JianShuTopicRepository
import com.ak47.cms.cms.entity.JianShuTopic
import com.ak47.cms.cms.tree.*
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
import java.util.*


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
    lateinit var jianShuTopicRepository: JianShuTopicRepository
    @Autowired
    lateinit var treeRepository: TreeRepository
    @Autowired
    lateinit var treeConsoleRepository: TreeConsoleRepository

    override fun run(vararg args: String) {
        // initTopics()
        // initTreeCountry()
        // initTreeConsole()
    }

    private fun initTreeConsole() {
        try {
            val record = treeConsoleRepository.findByCategory(TreeCategory.COUNTRY.name)
            if (record != null) {
                record.data = countryJson
                record.gmtModified = Date()
                treeConsoleRepository.save(record)
            } else {
                val treeConsole = TreeConsole()
                treeConsole.category = TreeCategory.COUNTRY.name
                treeConsole.data = countryJson
                treeConsoleRepository.save(treeConsole)
            }

        } catch (e: Exception) {
        }
    }

    private fun initTreeCountry() {
        logger.info("initTreeCountry")
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
                jianShuTopicRepository.save(jianshuTopic)
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


val countryJson = """
{"code":"","parentCode":"","name":"COUNTRY","category":"COUNTRY","children":[{"code":"100000","parentCode":"-1","name":"中国","category":"COUNTRY","children":[{"code":"110000","parentCode":"100000","name":"北京市","category":"COUNTRY","children":[]},{"code":"120000","parentCode":"100000","name":"天津市","category":"COUNTRY","children":[]},{"code":"130000","parentCode":"100000","name":"河北省","category":"COUNTRY","children":[]},{"code":"140000","parentCode":"100000","name":"山西省","category":"COUNTRY","children":[]},{"code":"150000","parentCode":"100000","name":"内蒙古自治区","category":"COUNTRY","children":[]},{"code":"210000","parentCode":"100000","name":"辽宁省","category":"COUNTRY","children":[]},{"code":"220000","parentCode":"100000","name":"吉林省","category":"COUNTRY","children":[]},{"code":"230000","parentCode":"100000","name":"黑龙江省","category":"COUNTRY","children":[]},{"code":"310000","parentCode":"100000","name":"上海市","category":"COUNTRY","children":[]},{"code":"320000","parentCode":"100000","name":"江苏省","category":"COUNTRY","children":[]},{"code":"330000","parentCode":"100000","name":"浙江省","category":"COUNTRY","children":[]},{"code":"340000","parentCode":"100000","name":"安徽省","category":"COUNTRY","children":[]},{"code":"350000","parentCode":"100000","name":"福建省","category":"COUNTRY","children":[]},{"code":"360000","parentCode":"100000","name":"江西省","category":"COUNTRY","children":[]},{"code":"370000","parentCode":"100000","name":"山东省","category":"COUNTRY","children":[]},{"code":"410000","parentCode":"100000","name":"河南省","category":"COUNTRY","children":[]},{"code":"420000","parentCode":"100000","name":"湖北省","category":"COUNTRY","children":[]},{"code":"430000","parentCode":"100000","name":"湖南省","category":"COUNTRY","children":[]},{"code":"440000","parentCode":"100000","name":"广东省","category":"COUNTRY","children":[]},{"code":"450000","parentCode":"100000","name":"广西壮族自治区","category":"COUNTRY","children":[]},{"code":"460000","parentCode":"100000","name":"海南省","category":"COUNTRY","children":[]},{"code":"500000","parentCode":"100000","name":"重庆市","category":"COUNTRY","children":[]},{"code":"510000","parentCode":"100000","name":"四川省","category":"COUNTRY","children":[]},{"code":"520000","parentCode":"100000","name":"贵州省","category":"COUNTRY","children":[]},{"code":"530000","parentCode":"100000","name":"云南省","category":"COUNTRY","children":[]},{"code":"540000","parentCode":"100000","name":"西藏自治区","category":"COUNTRY","children":[]},{"code":"610000","parentCode":"100000","name":"陕西省","category":"COUNTRY","children":[]},{"code":"620000","parentCode":"100000","name":"甘肃省","category":"COUNTRY","children":[]},{"code":"630000","parentCode":"100000","name":"青海省","category":"COUNTRY","children":[]},{"code":"640000","parentCode":"100000","name":"宁夏回族自治区","category":"COUNTRY","children":[]},{"code":"650000","parentCode":"100000","name":"新疆维吾尔自治区","category":"COUNTRY","children":[]},{"code":"810000","parentCode":"100000","name":"香港特别行政区","category":"COUNTRY","children":[]},{"code":"820000","parentCode":"100000","name":"澳门特别行政区","category":"COUNTRY","children":[]}]}]}
""".trimMargin()