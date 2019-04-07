$(function () {
    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN'])

    var searchText = $('.search').find('input').val()

    var columns = []
    columns.push(
        {
            title: 'ID',
            field: 'id',
            align: 'center',
            valign: 'middle',
            width: '10%',
            formatter: function (value, row, index) {
                return value
            }
        },
        {
            title: '标题',
            field: 'title',
            align: 'left',
            valign: 'middle',
            width: '20%',
            formatter: function (value, row, index) {
                var html = "<a href='" + row.url + "' target='_blank'>" + value + "</a>"
                return html
            }
        }, {
            title: '摘要',
            field: 'simpleContent',
            align: 'left',
            valign: 'middle',
            formatter: function (value, row, index) {
                return value
            }
        })

    $('#tech_article_view_table').bootstrapTable({
        url: 'listTechArticle',
        sidePagination: "server",
        queryParamsType: 'page,size',
        contentType: "application/x-www-form-urlencoded",
        method: 'get',
        striped: false,     //是否显示行间隔色
        buttonsAlign: 'right',
        smartDisplay: true,
        cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,  //是否显示分页（*）
        paginationLoop: true,
        paginationHAlign: 'right', //right, left
        paginationVAlign: 'bottom', //bottom, top, both
        paginationDetailHAlign: 'left', //right, left
        paginationPreText: ' 上一页',
        paginationNextText: '下一页',
        search: true,
        searchText: searchText,
        searchTimeOut: 500,
        searchAlign: 'right',
        searchOnEnterKey: false,
        trimOnSearch: true,
        sortable: true,    //是否启用排序
        sortOrder: "desc",   //排序方式
        sortName: "id",
        pageNumber: 1,     //初始化加载第一页，默认第一页
        pageSize: 10,      //每页的记录行数（*）
        pageList: [20, 50, 100, 200, 500, 1000], // 可选的每页数据
        totalField: 'totalElements', // 所有记录 count
        dataField: 'content', //后端 json 对应的表格List数据的 key
        columns: columns,
        queryParams: function (params) {
            return {
                size: params.pageSize,
                page: params.pageNumber - 1,
                sortName: params.sortName,
                sortOrder: params.sortOrder,
                searchText: params.searchText
            }
        },
        classes: 'table table-responsive',
    })

    var keyWord = getKeyWord()
    $('.search').find('input').val(keyWord)


})

function getKeyWord() {
    var url = decodeURI(location.href)
    var indexOfKeyWord = url.indexOf('?keyWord=')
    if (indexOfKeyWord != -1) {
        var start = indexOfKeyWord + '?keyWord='.length
        return url.substring(start)
    } else {
        return ""
    }
}
