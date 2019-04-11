$(function () {
    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN'])

    var searchText = $('.search').find('input').val()

    var columns = []
    columns.push(
        {
            title: 'Sets',
            field: 'imageBlob',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                var html = `<img onclick=addFavorite(${row.id}) width="100%" src="data:image/jpg;base64,${row.imageBlob}"/>`
                return html
            }
        }
    )

    $('#sotu_table').bootstrapTable({
        url: 'sotuSearchJson',
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
        paginationPreText: ' Pre',
        paginationNextText: 'Next',
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
        classes: 'table table-responsive full-width',
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
