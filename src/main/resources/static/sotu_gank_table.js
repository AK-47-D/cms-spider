$(function () {
    $.ajax({
        url: 'sotuGankSearchJson',
        type: 'GET',
        data: {page: 0, size: size},
        success: (res) => {
            var box = document.getElementById('box');
            appendChild(box, res);
            render(box.children);

            page = page + 1;
        },
        error: (err) => {
            console.log(err)
        }
    });
});


function render(items) {

    setTimeout(() => {
        waterFall(items);
    }, 1000)

    // 页面尺寸改变时实时触发
    window.onresize = function () {
        waterFall(items);
    };

    window.onscroll = function () {
        if (getClient().height + getScrollTop() >= items[items.length - 1].offsetTop) {
            // ajax 获取数据
            $.ajax({
                url: 'sotuGankSearchJson',
                type: 'GET',
                data: {page: page, size: size},
                success: (res) => {
                    var box = document.getElementById('box');
                    appendChild(box, res);
                    render(box.children);

                    page = page + 1;
                },
                error: (err) => {
                    console.log(err)
                }
            });
        }
    };
}


function waterFall(items) {
    // 列数  = 页面的宽度 / 图片的宽度
    var pageWidth = getClient().width;
    var itemWidth = items[0].offsetWidth;
    var columns = parseInt(pageWidth / (itemWidth + gap));
    var arr = [];
    for (var i = 0; i < items.length; i++) {
        if (i < columns) {
            // 确定第一行
            items[i].style.top = 0;
            items[i].style.left = (itemWidth + gap) * i + 'px';
            arr.push(items[i].offsetHeight);
        } else {
            // 其他行
            // 找到数组中最小高度  和 它的索引
            var minHeight = arr[0];
            var index = 0;
            for (var j = 0; j < arr.length; j++) {
                if (minHeight > arr[j]) {
                    minHeight = arr[j];
                    index = j;
                }
            }
            // 设置下一行的第一个盒子位置
            // top值就是最小列的高度 + gap
            items[i].style.top = arr[index] + gap + 'px';
            // left值就是最小列距离左边的距离
            items[i].style.left = items[index].offsetLeft + 'px';

            // 修改最小列的高度
            // 最小列的高度 = 当前自己的高度 + 拼接过来的高度 + 间隙的高度
            arr[index] = arr[index] + items[i].offsetHeight + gap;
        }
    }
}


function appendChild(box, res) {
    var datas = res.content;
    for (var i = 0; i < datas.length; i++) {
        var div = document.createElement("div");
        div.className = "item";
        div.innerHTML = `<img src="data:image/jpg;base64,${datas[i].imageBlob}"  onclick=addFavorite(${datas[i].id}) >`;
        box.appendChild(div);
    }
}

// clientWidth 处理兼容性
function getClient() {
    return {
        width: window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth,
        height: window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
    }
}

// scrollTop兼容性处理
function getScrollTop() {
    return window.pageYOffset || document.documentElement.scrollTop;
}

var size = 10;
var page = 0;
// 定义每一列之间的间隙 为10像素
var gap = 10;