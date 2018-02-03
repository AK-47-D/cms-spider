
package com.ak47.cms.cms.controller
import com.ak47.cms.cms.dao.CronTriggerDao
import com.ak47.cms.cms.entity.CronTrigger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import javax.servlet.http.HttpServletRequest
/**
     * Created by Kor on 2018-02-04 01:42:11. author: 东海陈光剑
     */
@RestController
@RequestMapping("/crontrigger")
class CronTriggerController {
    @Autowired lateinit var CronTriggerDao: CronTriggerDao
    @GetMapping(value = "/")
    fun crontrigger(request: HttpServletRequest): List<CronTrigger>{
        return CronTriggerDao.findAll()
    }
    @GetMapping(value = "/{id}")
    fun getOne(@PathVariable("id") id:Long): CronTrigger {
        return CronTriggerDao.getOne(id)
    }
}
