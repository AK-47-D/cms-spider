package com.ak47.cms.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CMSController {

    @GetMapping("calendarPage")
    public String calendarPage() {
        return "fin_news/calendar_page";
    }

    @GetMapping("manage/main")
    public String manageMain() {
        return "cms_manage/content";
    }


    @GetMapping("focusNewsPage")
    public String focusNewsPage() {
        return "fin_news/focus_news_page";
    }

    @GetMapping("newsPage")
    public String newsPage() {
        return "fin_news/news_page";
    }

    @GetMapping("reportPage")
    public String reportPage() {
        return "fin_news/report_page";
    }


}
