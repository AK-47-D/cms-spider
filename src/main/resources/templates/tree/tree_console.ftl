<#include '../common/head.ftl'>
<style>
    .editor {
        height: 600px;
    }

    .save-json-btn {
        width: 100%;
        color: #666;
        text-align: center;
        font-weight: bold;
        text-decoration: none;
        border-radius: 10px;
        font-size: 1.3em;
        background: #eee;
        margin: 0 0 5px 0;
        padding: 6px 0 6px 0;
    }

</style>

<input hidden id="category" value="${category}">

<button id="save-json-btn" class="save-json-btn">保存</button>
<div id="json_editor" class="editor"></div>

<#include '../common/foot.ftl'>

<script src="/tree/tree_console.js"></script>