<#include 'common/head.ftl'>

<form>
    <div class="col-lg-3">
        <div class="input-group">
            <input name="url"
                   id="add_input"
                   type="text"
                   class="form-control"
                   placeholder="输入简书专题 url ">
            <span class="input-group-btn">
						<button id="add_button"
                                class="btn-sm btn-default"
                                type="button">
							 保存
						</button>
			</span>
        </div>
    </div>
</form>

<table id="App"></table>

<#include 'common/foot.ftl'>

<#--这里需要从外部引入 js 文件, 因为 freemarker 的语法问题,导致 ES6 的字符串模板不能兼容,会报错-->
<script src="jianshu_topic.js"></script>
