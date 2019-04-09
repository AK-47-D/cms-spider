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
            title: 'url',
            field: 'url',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                var html = `<a href='${value}' target='_blank'>${value}</a>`
                return html
            }
        },
        {
            title: 'gmtCreated',
            field: 'gmtCreated',
            align: 'center',
            valign: 'middle'
        })

    $('#App').bootstrapTable({
        url: 'listJianShuTopic',
        sidePagination: "client",
        queryParamsType: 'page,size',
        contentType: "application/x-www-form-urlencoded",
        method: 'get',
        striped: false,     //是否显示行间隔色
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
        pageList: [20, 50, 100, 200, 500], // 可选的每页数据
        totalField: 'totalElements', // 所有记录 count
        dataField: 'content', //后端 json 对应的表格List数据的 key
        columns: columns,
        classes: 'table table-responsive full-width',
        responseHandler: function (res) {
            console.log(res)
            $('#App').bootstrapTable('getOptions').data = res.result;
            return res;
        }
    });


    $('#add_button').on('click', function () {
        var url = $('#add_input').val()
        $.ajax({
            url: 'saveJianShuTopic',
            type: 'post',
            data: {url: url},
            success: function (response) {
                if (response.success == true) {
                    alert("Saved")
                    $('#App').bootstrapTable('refresh')
                } else {
                    alert("Error:" + response.message)
                }

            },
            error: function (error) {
                alert(JSON.stringify(error))
            }
        })
    })

})