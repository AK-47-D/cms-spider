package com.ak47.cms.cms

import com.ak47.cms.cms.service.JsonResultProcessor
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class SimpleTest {

    @Test
    fun test() {
        val content = JsonResultProcessor.getUrlContent("http://gank.io/builder/data/%E7%A6%8F%E5%88%A9/100/1")
        println(content)
    }
}