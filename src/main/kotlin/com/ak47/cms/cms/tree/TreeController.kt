package com.ak47.cms.cms.tree

import com.ak47.cms.cms.result.Result
import com.ak47.cms.cms.vo.TreeVO
import com.alibaba.fastjson.JSON
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class TreeController {
    val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var treeConsoleRepository: TreeConsoleRepository

    @GetMapping("/api/tree.json")
    fun tree(category: String): Result<TreeVO> {
        try {
            val treeConsole = treeConsoleRepository.findByCategory(category)
            if (treeConsole != null) {
                val data = treeConsole.data
                val vo = JSON.parseObject(data, TreeVO::class.java)
                return Result(data = vo, sucesss = true, message = "")
            } else {
                return Result(data = null, sucesss = true, message = "")
            }
        } catch (e: Exception) {
            logger.error("/api/tree.json:", e)
            return Result(data = null, sucesss = false, message = e.message)
        }
    }

    @PostMapping("/api/saveTree.json")
    fun saveTree(@RequestParam("category") category: String,
                 @RequestParam("data") data: String): Result<String> {
        try {
            val treeConsoleInDB = treeConsoleRepository.findByCategory(category)
            if (treeConsoleInDB != null) {
                treeConsoleInDB.data = data
                treeConsoleInDB.gmtModified = Date()
                treeConsoleRepository.save(treeConsoleInDB)
                return Result(data = null, sucesss = true, message = "")
            } else {
                val treeConsoleNew = TreeConsole()
                treeConsoleNew.data = data
                treeConsoleNew.category = category
                treeConsoleRepository.save(treeConsoleNew)
                return Result(data = null, sucesss = true, message = "")
            }
        } catch (e: Exception) {
            logger.error("data:{}", data)
            logger.error("/api/saveTree.json:", e)
            return Result(data = null, sucesss = false, message = e.message)
        }

    }
}

