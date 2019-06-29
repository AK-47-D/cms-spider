package com.ak47.cms.cms

import com.ak47.cms.cms.dao.JianShuTopicRepository
import com.ak47.cms.cms.entity.JianShuTopic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
class CmsApplication

fun main(args: Array<String>) {
    runApplication<CmsApplication>(*args)
}


@Component
@Order(value = Ordered.LOWEST_PRECEDENCE)
class InitRunner : CommandLineRunner {
    @Autowired
    lateinit var JianShuTopicRepository: JianShuTopicRepository

    override fun run(vararg args: String) {
        val 简书专题URLs = arrayOf(
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

        简书专题URLs.forEach {
            try {
                val jianshuTopic = JianShuTopic()
                jianshuTopic.url = it
                JianShuTopicRepository.save(jianshuTopic)
            } catch (e: Exception) {

            }
        }

    }
}