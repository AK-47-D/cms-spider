package com.ak47.cms.cms

import com.ak47.cms.cms.dao.SearchKeyWordRepository
import com.ak47.cms.cms.entity.SearchKeyWord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.io.File

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = arrayOf(ErrorMvcAutoConfiguration::class))
class CmsApplication

fun main(args: Array<String>) {
    SpringApplication.run(CmsApplication::class.java, *args)
}

@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
class initSearchKeyWordRunner : CommandLineRunner {
    @Autowired lateinit var searchKeyWordRepository: SearchKeyWordRepository
    override fun run(vararg args: String) {
        try {
            var keyWords = File("搜索关键词列表.data").readLines()
            keyWords.forEach {
                val SearchKeyWord = SearchKeyWord()
                SearchKeyWord.keyWord = it
                searchKeyWordRepository.saveOnNoDuplicateKey(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
