<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">I9102</a>
        </div>
        <div>
            <ul class="nav navbar-nav">


                <li class="<#if requestURI?if_exists =="/sotu_gank_view">active</#if>">
                    <a href="sotu_gank_view">干货福利</a>
                </li>

                <li class="<#if requestURI?if_exists =="/sotu_favorite_view">active</#if>">
                    <a href="sotu_favorite_view">精选收藏</a>
                </li>

                <li class="<#if requestURI?if_exists =="/sotu_view">active</#if>">
                    <a href="sotu_view">图片列表</a>
                </li>


                <li class="<#if requestURI?if_exists =="/sotu_huaban_view">active</#if>">
                    <a href="sotu_huaban_view">花瓣美女</a>
                </li>


                <li class="<#if requestURI?if_exists =="/focusNewsPage">active</#if>">
                    <a href="focusNewsPage">实时财经</a>
                </li>

                <li class="<#if requestURI?if_exists =="/tech_article_view">active</#if>">
                    <a href="tech_article_view">技术文章</a>
                </li>

                <li class=" <#if requestURI?if_exists =="/search_keyword_view">active</#if>"><a
                        href="search_keyword_view">搜索关键字</a></li>




                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        程序设计 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="http://www.jianshu.com/nb/12976878" target="_blank">Kotlin 极简教程</a></li>
                        <li><a href="http://www.jianshu.com/nb/17117730" target="_blank">Kotlin 项目实战开发</a></li>
                        <li><a href="#">SpringBoot</a></li>
                        <li><a href="#">Java</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Scala</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Groovy</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        光剑文集 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="https://jason-chen-2017.github.io/Jason-Chen-2017/poem/%E6%98%A5%E5%BF%83%E6%B2%81%E9%80%8F:99%E9%A6%96.html"
                               target="_blank">春心沁透: 99首</a></li>
                        <li>
                            <a href="https://jason-chen-2017.github.io/Jason-Chen-2017/poem/%E5%A4%9C%E7%B2%BE%E7%81%B5:53%E9%A6%96.html"
                               target="_blank">夜精灵:53首</a></li>
                        <li>
                            <a href="https://jason-chen-2017.github.io/Jason-Chen-2017/poem/%E8%87%AA%E6%B8%A1:125%E9%A6%96.html"
                               target="_blank">自渡:125首</a></li>
                        <li>
                            <a href="https://jason-chen-2017.github.io/Jason-Chen-2017/poem/%E9%9D%92%E7%8E%89%E6%A1%88:27%E9%A6%96.html"
                               target="_blank">青玉案: 27首</a></li>
                        <li>
                            <a href="https://jason-chen-2017.github.io/Jason-Chen-2017/poem/%E6%8B%BE%E5%8F%B6:24%E9%A6%96.html"
                               target="_blank">拾叶: 24首</a></li>
                        <li>
                            <a href="https://jason-chen-2017.github.io/Jason-Chen-2017/poem/%E4%BA%BA%E9%97%B4%E5%91%B3:60%E9%A6%96.html"
                               target="_blank">人间味:60首</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        系统任务 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="doSogouImageCrawJob" target="_blank">抓取搜狗图片</a></li>
                        <li><a href="doGankImageCrawJob" target="_blank">抓取干货福利图</a></li>
                        <li><a href="doHuaBanImageCrawJob" target="_blank">抓取花瓣美女</a></li>
                        <li><a href="doCrawITEyeTechArticle" target="_blank">抓取ITEye技术文章</a></li>
                        <li><a href="doCrawJianShuTechArticle" target="_blank">抓取简书文章</a></li>
                        <li><a href="doBatchUpdateJob" target="_blank">更新图片总数</a></li>
                    </ul>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#">关于</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


