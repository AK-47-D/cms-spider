package com.ak47.cms.cms.api;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

/**
 *  东海陈光剑 2017/11/3; 2019/04/07
 */
public class CrawlerWebClient {
    private static CrawlerWebClient crawlerWebClient = null;

    public static CrawlerWebClient instanceCrawlerClient() {
        if (crawlerWebClient == null) {
            crawlerWebClient = new CrawlerWebClient();
        }
        return crawlerWebClient;
    }

    private CrawlerWebClient() {

    }

    private WebClient webClient = null;

    public WebClient instanceWebClient(Integer javaScriptTimeout) {
        if (webClient == null) {
            webClient = new WebClient();
        }
        if (javaScriptTimeout != null) {
            webClient.setJavaScriptTimeout(javaScriptTimeout);
        }
        webClient.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true
        webClient.getOptions().setCssEnabled(false); //禁用css支持
        webClient.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常
        return webClient;
    }

    public WebClient instanceWebClient() {
        return instanceWebClient(null);
    }

    public HtmlPage getPage(String url) throws IOException {
        WebClient webClient = instanceWebClient();
        return webClient.getPage(url);
    }


    /**
     * 使用 WebClient 方案,避免服务端 403
     * @param url
     * @return
     * @throws IOException
     */
    public String getJson(String url) throws IOException {
        WebClient webClient = instanceWebClient();
        Page page = webClient.getPage(url);
        WebResponse response = page.getWebResponse();
        if (response.getContentType().equals("application/json")) {
            return response.getContentAsString();
        }
        return null;
    }


    public void webClientClose() {
        if (webClient == null) {
            webClient.close();
            webClient = null;
        }
    }
}
