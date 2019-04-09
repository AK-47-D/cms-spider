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


<script src="jianshu_topic.js"></script>
