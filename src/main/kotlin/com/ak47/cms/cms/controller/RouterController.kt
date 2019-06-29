package com.ak47.cms.cms.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

@Controller
class RouterController {

    @RequestMapping(value = *arrayOf("sotu_view"))
    fun sotu_view(model: Model, request: HttpServletRequest): ModelAndView {
        model.addAttribute("requestURI", request.requestURI)
        return ModelAndView("sotu_view")
    }

    @RequestMapping(value = "sotu_huaban_view")
    fun sotu_huaban_view(model: Model, request: HttpServletRequest): ModelAndView {
        model.addAttribute("requestURI", request.requestURI)
        return ModelAndView("sotu_huaban_view")
    }

    @RequestMapping(value = *arrayOf("/", "sotu_favorite_view"))
    fun sotu_favorite_view(model: Model, request: HttpServletRequest): ModelAndView {
        model.addAttribute("requestURI", request.requestURI)
        return ModelAndView("sotu_favorite_view")
    }

    @GetMapping("tech_article_view")
    fun tech_article_view(model: Model, request: HttpServletRequest): ModelAndView {
        model.addAttribute("requestURI", request.requestURI)
        return ModelAndView("tech_article_view")
    }

    @GetMapping("jianshu_topic")
    fun jianshu_topic(model: Model, request: HttpServletRequest): ModelAndView {
        model.addAttribute("requestURI", request.requestURI)
        return ModelAndView("jianshu_topic")
    }
}