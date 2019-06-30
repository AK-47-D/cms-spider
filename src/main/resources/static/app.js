function addFavorite(id) {
    $.ajax({
        url: 'addFavorite',
        data: {id: id},
        dataType: 'json',
        type: 'post',
        success: function (resp) {
            // alert(JSON.stringify(resp))
            new PNotify({
                title: 'Add Favorite',
                styling: 'bootstrap3',
                text: 'Loved!',
                type: 'success',
                delay: 500,
            });
        },
        error: function (msg) {
            // alert(JSON.stringify(msg))
            new PNotify({
                title: 'Collect',
                styling: 'bootstrap3',
                text: JSON.stringify(msg),
                type: 'error',
                delay: 500,
            });
        }
    })
}

function deleteById(id) {
    $.ajax({
        url: 'delete',
        data: {id: id},
        dataType: 'json',
        type: 'post',
        success: function (resp) {
            // alert(JSON.stringify(resp))
            $('#sotu_favorite_table').bootstrapTable('refresh')
            $('#sotu_table').bootstrapTable('refresh')
            new PNotify({
                title: 'Delete',
                styling: 'bootstrap3',
                text: JSON.stringify(resp),
                type: 'info',
                delay: 500,
            });

        },
        error: function (msg) {
            // alert(JSON.stringify(msg))
            new PNotify({
                title: 'Delete',
                styling: 'bootstrap3',
                text: JSON.stringify(msg),
                type: 'error',
                delay: 500,
            });
        }
    })
}


function downloadImage(src) {
    var $a = $("<a></a>").attr("href", src).attr("download", "sotu.png");
    $a[0].click();
}


function downBase64Image(url) {
    var blob = base64Img2Blob(url);
    url = window.URL.createObjectURL(blob);
    var timestamp = (new Date()).getTime();
    var $a = $("<a></a>").attr("href", url).attr("download", `I9102_${timestamp}.png`);
    $a[0].click();
}


function base64Img2Blob(code) {
    var parts = code.split(';base64,');
    var contentType = parts[0].split(':')[1];
    var raw = window.atob(parts[1]);
    var rawLength = raw.length;
    var uInt8Array = new Uint8Array(rawLength);
    for (var i = 0; i < rawLength; ++i) {
        uInt8Array[i] = raw.charCodeAt(i);
    }
    return new Blob([uInt8Array], {type: contentType});
}

function downloadFile(fileName, content) {
    var aLink = document.createElement('a');
    var blob = base64Img2Blob(content); //new Blob([content]);
    var evt = document.createEvent("HTMLEvents");
    evt.initEvent("click", false, false);//initEvent 不加后两个参数在FF下会报错
    aLink.download = fileName;
    aLink.href = URL.createObjectURL(blob);
    aLink.dispatchEvent(evt);
}

function slideShow(index) {
    var urlArray = []
    var size = $('img').length
    for (var i = 0; i < size; i++) {
        urlArray.push($($('img')[i]).attr('data-original'))
    }

    var nextLoop = setInterval(function () {
        $('#picNext').click()
    }, 5000)

    fnSlideShow(urlArray, index, nextLoop);
}

$(function () {
    $(document).on('keydown', function (event) {
        // 键盘翻页事件
        var e = event || window.event || arguments.callee.caller.arguments[0];

        if (e && e.keyCode == 38) {//上
            // 上一张图片
            $('#picPrev').click()
        }

        if (e && e.keyCode == 40) {//下
            // 下一张图片
            $('#picNext').click()

        }

        if (e && e.keyCode == 37) {//左
            // Pre
            $('.page-pre').click()
        }

        if (e && e.keyCode == 39) {//右
            // Next
            $('.page-next').click()

        }
    })
})

Date.prototype.Format = function (formatStr) {
    var str = formatStr;
    var Week = ['日', '一', '二', '三', '四', '五', '六'];

    str = str.replace(/yyyy|YYYY/, this.getFullYear());
    str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100) : '0' + (this.getYear() % 100));

    str = str.replace(/MM/, this.getMonth() > 8 ? this.getMonth()+1 : '0' + (this.getMonth()+1));
    str = str.replace(/M/g, this.getMonth()+1);

    str = str.replace(/w|W/g, Week[this.getDay()]);

    str = str.replace(/dd|DD/, this.getDate() > 9 ?this.getDate() : '0' + this.getDate());
    str = str.replace(/d|D/g, this.getDate());

    str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours() : '0' + this.getHours());
    str = str.replace(/h|H/g, this.getHours());
    str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes() : '0' + this.getMinutes());
    str = str.replace(/m/g, this.getMinutes());

    str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds() : '0' + this.getSeconds());
    str = str.replace(/s|S/g, this.getSeconds());

    return str;
}


/**
 * @Name：日历控件
 * @Description：日历控件
 * @author：Huayf
 * @date：2015年1月12日下午
 */
var calender = new function (){
    var evnClick ;

    this.init = initCalendar;  // 初始化日历控件
    this.getDate = getDisDate; // 获取当前所选的日期
    this.setLastMonth = setLastMonth;
    this.setNextMonth = setNextMonth;
    /*
     * 初始化时间控件，传入要渲染div的id与日历的点击事件
     * @params: code 需要渲染的div code
     * @params: evn 日历的点击时间
     */
    function initCalendar(id,evn){
        var calendarHtml = '<div class="calendar"><p class="calendar-year" code="pcalyear">2015年</p><a href="javascript:calender.setLastMonth();" class="calendar-btn calendar-btn-l"><span class="icon-triangle-w"><</span></a><a href="javascript:calender.setNextMonth();" class="calendar-btn  calendar-btn-r"><span class="icon-triangle-e">></span></a><div class="calendar-months"><ul class="calendar-months" code="calmonthli"><li>1月</li><li>2月</li><li>3月</li><li class="months-cur">4月</li><li>5月</li><li>6月</li><li>7月</li><input type="hidden" value="" code="hidyear0" /><input type="hidden" value="" code="hidyear1" /><input type="hidden" value="" code="hidyear2" /><input type="hidden" value="" code="hidyear3" /><input type="hidden" value="" code="hidyear4" /><input type="hidden" value="" code="hidyear5" /><input type="hidden" value="" code="hidyear6" /></ul></div><div class="calendar-day" ><ul class="week week-b week-hd" ><li>日</li><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li><li>六</li></ul><ul class="week week-b week-day week-day-b" code="caldayli"><li></li><li></li><li></li><li></li><li></li><li></li><li class="duty-full"><span>2</span><p>全天</p></li></ul></div></div><div  class="title-full" code="disDate"></div><input type="hidden" value="" code="selectYear"><input type="hidden" value="" code="selectMonth"><input type="hidden" value="" code="selectDay"><input type="hidden" value="3" code="selectMonIndex">';
        $("#"+id).html(calendarHtml);
        evnClick = evn;
        initTime();
        setCalendar();
        $("#calmonthli li").click(function(){
            setMonthStyle(this);
        })
    }

    /*
     * 初始化时间
     */
    function initTime(){
        var nowtime = new Date();
        var selectYear = nowtime.getYear()+1900;//当前选择年
        var selectMonth = nowtime.getMonth();//当前选择月
        var selectDay = nowtime.getDate();//当前选择日
        $("#selectYear").val(selectYear);
        $("#selectMonth").val(selectMonth);
        $("#selectDay").val(selectDay);
        $("#pcalyear").html(selectYear + "年");//改变显示年
        setDisDate();//改变显示日期
        var hidyear = selectYear;
        selectMonth += 1;
        //初始化月份列表，以及月份对应的年
        for(var i=0;i<7;i++){
            if(selectMonth-(3-i)>12){
                $("#calmonthli li").eq(i).html(selectMonth-(3-i+12)+"月");
                $("#hidyear"+i).val(hidyear+1);
            }
            else if(selectMonth-(3-i)<=0){
                $("#calmonthli li").eq(i).html(selectMonth-(3-i-12)+"月");
                $("#hidyear"+i).val(hidyear-1);
            }
            else{
                $("#calmonthli li").eq(i).html(selectMonth-(3-i)+"月");
                $("#hidyear"+i).val(hidyear);
            }
        }
    }

    /*
     * 选择月的日历
     */
    function setCalendar(){
        clearCalendar();
        var selectYear = $("#selectYear").val();
        var selectMonth = $("#selectMonth").val();
        var selectDay = $("#selectDay").val();
        var dmonth = selectMonth*1 +1;//获取当月最后一天所用月份（下个月的第0天）
        var dayNum = new Date(selectYear,dmonth,0);//获取当月的最后一天
        dayNum = dayNum.getDate();
        var selectMonthDay = new Date(selectYear,selectMonth,1);//选择月份的第一天
        var firstDayWeek = selectMonthDay.getDay();//获取第一天的星期
        for(var i=firstDayWeek,j=1;i<=$(".week-day-b li").length&&j<=dayNum;i++,j++){
            $(".week-day-b li").eq(i).html("<span>"+j+"</span>");//填写日期
            if(j == selectDay)
                $(".week-day-b li").eq(i).addClass("duty-cur");
            if(i==$(".week-day-b li").length-1&&j!=dayNum){//如果日历格子不够再画一行
                for(var k=0;k<7;k++){
                    $("#caldayli").append("<li></li>");
                }
            }
        }

        $("#caldayli li").click(function(){//绑定日历li点击事件
            setDayStyle(this);
            if (evnClick) {
                evnClick();
            }
        })
    }

    /*
     * 清空日历
     */
    function clearCalendar(){
        $("#caldayli").html("");
        for(var k=0;k<7;k++){
            $("#caldayli").append("<li></li>");
        }
    }
    /*
     * 选择日期
     */
    function setDayStyle(dom){
        $(".week-day-b li").removeClass("duty-cur");
        $(dom).addClass("duty-cur");
        var v = dom.firstChild.innerHTML;//获取点击结点日期
        $("#selectDay").val(v);//改变当前选择日期
        setDisDate();//改变显示日期
    }
    /*
     * 选择月份
     */
    function setMonthStyle(dom){
        $(".calendar-months li").removeClass("months-cur");
        $(dom).addClass("months-cur");
        $("#selectMonIndex").val($(dom).index());//设置选中月下标
        var v = $(dom).html();//获取选择月份
        if(v.length==3)
            v = v.substring(0,2);
        else
            v = v.substring(0,1);
        v = v-1;
        $("#selectMonth").val(v);//改变选择月
        var index = $(dom).index();//获取月份li对应索引
        var selectYear = $("#hidyear" + index).val();//获得选择年
        $("#selectYear").val(selectYear);//改变选择年
        $("#pcalyear").html(selectYear+ "年");//改变显示年
        setDisDate();//变更显示日期
        setCalendar();//变更日历
    }
    /*
     * 设置Next月份，点一下翻半年
     */
    function setNextMonth(){
        for(var i=0;i<7;i++){
            var m = $("#calmonthli li").eq(i).html();
            m = m.substring(0,m.length-1);
            if((m*1+6)>12){//设置对应月份的年份
                var hidyear = $("#hidyear"+i).val();
                $("#hidyear"+i).val(hidyear*1+1);
            }
            if((m*1+6) == 12)
                $("#calmonthli li").eq(i).html(12+"月");
            else
                $("#calmonthli li").eq(i).html((m*1+6)%12+"月");//设置当月li
        }
        //改变选择月
        var selectMonth = $("#selectMonth").val();
        $("#selectMonth").val((selectMonth*1+6)%12);
        //改变选择年
        var index = $("#selectMonIndex").val();//获取选中月下标
        var syear = $("#hidyear"+index).val();//获取选中月对应年
        $("#selectYear").val(syear);//设置选中年
        $("#pcalyear").html(syear+ "年");//改变显示年
        //改变日历
        setCalendar();
        setDisDate();//改变显示日期
    }
    /*
     * 设置Pre月份,点一下翻半年
     */
    function setLastMonth(){
        for(var i=0;i<7;i++){
            var m = $("#calmonthli li").eq(i).html();
            m = m.substring(0,m.length-1);
            if((m*1-6)<0){//设置对应月份的年份
                var hidyear = $("#hidyear"+i).val();
                $("#hidyear"+i).val(hidyear*1-1);
            }
            if((m*1-6+12) == 12)
                $("#calmonthli li").eq(i).html(12+"月");
            else
                $("#calmonthli li").eq(i).html((m*1-6+12)%12+"月");//设置当月li
        }
        //改变选择月
        var selectMonth = $("#selectMonth").val();
        $("#selectMonth").val((selectMonth*1-6+12)%12);
        //改变选择年
        var index = $("#selectMonIndex").val();//获取选中月下标
        var syear = $("#hidyear"+index).val();//获取选中月对应年
        $("#selectYear").val(syear);//设置选中年
        $("#pcalyear").html(syear+ "年");//改变显示年
        //改变日历
        setCalendar();
        setDisDate();//改变显示日期
    }
    /*
     * 设置显示日期
     */
    function setDisDate(){
        var selectYear = $("#selectYear").val();//获取选中年月日
        var selectMonth = $("#selectMonth").val();
        var selectDay = $("#selectDay").val();
        var now = getDisDate();
        var selectWeek = now.getDay();//获取选中天数日期
        selectWeek = getweek(selectWeek);
        //设置年月日
        $("#disDate").html(selectYear+"年 "+(selectMonth*1+1)+"月 "+selectDay+"日  星期" +selectWeek);
    }
    /*
     * 设置显示日期
     * return: 当前选择日期
     */
    function getDisDate(){
        var selectYear = $("#selectYear").val();//获取选中年月日
        var selectMonth = $("#selectMonth").val();
        var selectDay = $("#selectDay").val();
        return new Date(selectYear,selectMonth,selectDay);
    }
    /*
     * 获得星期
     */
    function getweek(day){
        var week = "";
        switch(day){
            case 0:week="日";break;
            case 1:week="一";break;
            case 2:week="二";break;
            case 3:week="三";break;
            case 4:week="四";break;
            case 5:week="五";break;
            case 6:week="六";break;
        }
        return week;
    }

}


/**
 * downCount: Simple Countdown clock with offset
 * Author: Sonny T. <hi@sonnyt.com>, sonnyt.com
 */

(function ($) {

    $.fn.downCount = function (options, callback) {
        var settings = $.extend({
            date: null,
            offset: null
        }, options);

        // Throw error if date is not set
        if (!settings.date) {
            $.error('Date is not defined.');
        }

        // Throw error if date is set incorectly
        if (!Date.parse(settings.date)) {
            $.error('Incorrect date format, it should look like this, 12/24/2012 12:00:00.');
        }

        // Save container
        var container = this;

        /**
         * Change client's local date to match offset timezone
         * @return {Object} Fixed Date object.
         */
        var currentDate = function () {
            // get client's current date
            var date = new Date();

            // turn date to utc
            var utc = date.getTime() + (date.getTimezoneOffset() * 60000);

            // set new Date object
            var new_date = new Date(utc + (3600000*settings.offset))

            return new_date;
        };

        /**
         * Main downCount function that calculates everything
         */
        function countdown () {
            var target_date = new Date(settings.date), // set target date
                current_date = currentDate(); // get fixed current date

            // difference of dates
            var difference = target_date - current_date;

            // if difference is negative than it's pass the target date
            if (difference < 0) {
                // stop timer
                clearInterval(interval);

                if (callback && typeof callback === 'function') callback();

                return;
            }

            // basic math variables
            var _second = 1000,
                _minute = _second * 60,
                _hour = _minute * 60,
                _day = _hour * 24;

            // calculate dates
            var days = Math.floor(difference / _day),
                hours = Math.floor((difference % _day) / _hour),
                minutes = Math.floor((difference % _hour) / _minute),
                seconds = Math.floor((difference % _minute) / _second);

            // fix dates so that it will show two digets
            days = (String(days).length >= 2) ? days : '0' + days;
            hours = (String(hours).length >= 2) ? hours : '0' + hours;
            minutes = (String(minutes).length >= 2) ? minutes : '0' + minutes;
            seconds = (String(seconds).length >= 2) ? seconds : '0' + seconds;

            // based on the date change the refrence wording
            var ref_days = (days === 1) ? 'day' : 'days',
                ref_hours = (hours === 1) ? 'hour' : 'hours',
                ref_minutes = (minutes === 1) ? 'minute' : 'minutes',
                ref_seconds = (seconds === 1) ? 'second' : 'seconds';

            // set to DOM
            container.find('.days').text(days);
            container.find('.hours').text(hours);
            container.find('.minutes').text(minutes);
            container.find('.seconds').text(seconds);

            container.find('.days_ref').text(ref_days);
            container.find('.hours_ref').text(ref_hours);
            container.find('.minutes_ref').text(ref_minutes);
            container.find('.seconds_ref').text(ref_seconds);
        };

        // start
        var interval = setInterval(countdown, 1000);
    };

});

/**
 * @Name：可复用的日期控件
 * @author：cqf
 * @date：2017年11月12日下午
 */
var timebar = new function () {

    this.init = initTimeBar;  // 初始化日历控件
    this.getDate = getDisDate; // 获取当前所选的日期
    this.nextTime = nextTime;
    this.lastTime = lastTime;
    this.changeTime = changeTime;

    var now = new Date();
    var todayFlag = 1;

    /*
     * 初始化时间控件，传入要渲染div的id，和时间的点击事件
     */
    function initTimeBar(id, evn) {
        var timeHtml = '<p code="'+id+'Text" class="calendar-year calyearp"></p>' +
            '<a style="display: inline-block;margin-top: 15px;" href="javascript:timebar.nextTime('+id+');" class="mt20 calendar-btn calendar-btn-l"><span class="glyphicon glyphicon-chevron-left"></span></a>' +
            '<a style="display: inline-block;margin-top: 15px;" href="javascript:timebar.lastTime('+id+');" class="mt20 calendar-btn calendar-btn-r"><span class="glyphicon glyphicon-chevron-right"></span></a>' +
            '<div class="calendar-day">' +
            '<ul class="week week-hd">' +
            '<li class="wli1">日</li>' +
            '<li class="wli2">一</li>' +
            '<li class="wli3">二</li>' +
            '<li class="wli4">三</li>' +
            '<li class="wli5">四</li>' +
            '<li class="wli6">五</li>' +
            '<li class="wli7">六</li>' +
            '</ul>' +
            '<ul class="week week-day dul" >' +
            '<li class="dli1"><span>1</span><input type="hidden" value=""/></li>' +
            '<li class="dli2"><span>2</span><input type="hidden" value=""/></li>' +
            '<li class="dli3"><span>3</span><input type="hidden" value=""/></li>' +
            '<li class="dli4 duty-cur"><span>4</span><input type="hidden" value=""/></li>' +
            '<li class="dli5"><span>5</span><input type="hidden" value=""/></li>' +
            '<li class="dli6"><span>6</span><input type="hidden" value=""/></li>' +
            '<li class="dli7"><span>7</span><input type="hidden" value=""/></li>' +
            '</ul>' +
            '</div>';
        debugger;
        $('#' + id).html(timeHtml);
        showTime(id);//初始化时间
        // var distime = $("#dli4 input").val();
        var distime = $('#' + id).find(".dli4 input").val();
        changeTime(distime,id);//改变显示时间
        changeTimeStyle(id);//改变选中节点样式
        $("#" + id).find(".dul li").click(function () {
            debugger;
            clickTime(id,this.className);
            if (evn) {
                evn();
            }
        })
    }

    /*
     * 时间显示
     */
    function showTime(id) {
        var d = new Array(7);
        //var now = new Date();
        d[3] = now;
        d[2] = getLastDay(now);
        d[1] = getLastDay(d[2]);
        d[0] = getLastDay(d[1]);
        d[4] = getNextDay(now);
        d[5] = getNextDay(d[4]);
        d[6] = getNextDay(d[5]);
        setDataText(d,id);
        changeTimeStyle(id);
    }

    /*
     * 通过时间给文本赋值
     */
    function setDataText(d,id) {
        for (let i = 0; i < 7; i++) {
            let year = d[i].getYear() + 1900;
            let sunday = d[i].getDay();
            let month = d[i].getMonth() + 1;
            let day = d[i].getDate();
            $('#'+id).find(".wli" + (i + 1)).html(getweek(sunday));//给星期文本复制
            $('#'+id).find(".dli" + (i + 1) + " span").html(day);//给日期文本复制
            if (month < 10)
                month = "0" + month;
            if (day < 10)
                day = "0" + day;
            $('#'+id).find(".dli" + (i + 1) + " input").val(year + "-" + month + "-" + day);//给隐藏日期赋值
            //周六周日边变红
            if (getweek(sunday) == "日" || getweek(sunday) == "六") {
                $('#'+id).find(".wli" + (i + 1)).css("color", "red");
            }
            else {
                $('#'+id).find(".wli" + (i + 1)).css("color", "black");
            }
        }
    }

    /*
     * 设置显示日期
     * return: 当前选择日期
     */
    function getDisDate() {
        var time = $("#" + id + " input").val();
        var t = time.split("-");
        if (t[1].length == 1) t[1] = "0" + t[1];
        if (t[2].length == 1) t[2] = "0" + t[2];
        return new Date(t[0], t[1] - 1, t[2]);
    }

    /*
     * 获取时间
     */
    function clickTime(id,clickClass) {
        debugger;
        if(clickClass.includes('duty-cur')){
            return;
        }
        if (todayFlag) {
            $('#' + id).find(".dul li span").addClass('duty-prev')
        }
        $('#' + id).find(".dul li").removeClass("duty-cur");
        $("#" + id).find(".dul ."+clickClass).addClass("duty-cur");
        var time = $("#" + id).find(".dul ."+clickClass).find('input').val();
        debugger;
        changeTime(time,id);
        todayFlag = 0;

    }

    /*
     *改变选中显示时间
     */
    function changeTime(time,id) {
        var t = time.split("-");
        if (t[1].length == 1)
            t[1] = "0" + t[1];
        if (t[2].length == 1)
            t[2] = "0" + t[2];
        // $('#'+code).find(".calendar-year").html(t[0] + "年" + t[1] + "月" + t[2] + "日");
        $('#'+id+'Text').html(t[0] + "年" + t[1] + "月" + t[2] + "日");
        cale.caleAjax(time,id)
    }

    /*
     * 改变选中结点样式
     */
    function changeTimeStyle(id) {

        $('#'+id).find(".dul li").removeClass("duty-cur");
        var time = $(".calendar-year").html();
        var y = time.substring(0, 4);
        var m = time.substring(5, 7);
        var d = time.substring(8, 10);
        time = y + "-" + m + "-" + d;
        for (var i = 0; i < 7; i++) {
            if ($('#'+id).find(".dli" + (i + 1) + " input").val() == time)
                $('#'+id).find(".dli" + (i + 1)).addClass("duty-cur");

        }
    }

    /*
     * 时间切换,向左滚动,后退（左箭头点击事件）
     */
    function nextTime(id) {
        now = getLastDay(now);
        showTime(id.id);
    }

    /*
     * 时间切换,向右滚动,前进（右箭头点击事件）
     */
    function lastTime(id) {
        now = getNextDay(now);
        showTime(id.id);
    }

    /*
     * 获取后一天的时间
     */
    function getNextDay(d) {
        d = new Date(d);
        d = +d + 1000 * 60 * 60 * 24;
        d = new Date(d);
        return d;
    }

    /*
     * 获取前一天的时间
     */
    function getLastDay(d) {
        d = new Date(d);
        d = +d - 1000 * 60 * 60 * 24;
        d = new Date(d);
        return d;
    }

    /*
     * 获取星期
     */
    function getweek(day) {
        var week = "";
        switch (day) {
            case 0:
                week = "日";
                break;
            case 1:
                week = "一";
                break;
            case 2:
                week = "二";
                break;
            case 3:
                week = "三";
                break;
            case 4:
                week = "四";
                break;
            case 5:
                week = "五";
                break;
            case 6:
                week = "六";
                break;
        }
        return week;
    }

}




var size = 30;
var page = 0;
// 定义每一列之间的间隙 为10像素
var gap = 10;

function fetchAndRender(url, method, data) {
    $.ajax({
        url: url,
        type: method,
        data: data,
        success: (res) => {
            var box = document.getElementById('box');
            var items = box.children;
            appendChild(box, res);
            render(items);
            // 图片幻灯效果
            photoviewer();
        },
        error: (err) => {
            console.log(err)
        }
    });
}

// 页面尺寸改变时实时触发
window.onresize = function () {
    var box = document.getElementById('box');
    waterFall(box.children);
};

function render(items) {
    setTimeout(() => {
        waterFall(items);
    }, 1000)
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
        div.innerHTML = `<img src="data:image/jpg;base64,${datas[i].imageBlob}"  
                              data-gallery="manual">
                              <div onclick="addFavorite(${datas[i].id})" 
                                   class="glyphicon glyphicon-heart pull-right" 
                                   style="font-size: 1.5em;color: #ff0000a8;">
                                   <span style="font-size: 0.8em;">${datas[i].loveCount}</span>
                              </div>
        `;
        box.appendChild(div);
    }
}

// clientWidth 处理兼容性
function getClient() {
    return {
        width: window.screen.width || window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth,
        height: window.screen.height || window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
    }
}

function photoviewer() {

    $('[data-gallery=manual]').click(function (e) {

        e.preventDefault();

        var items = [],
            // get index of element clicked
            options = {
                index: $(this).index(),
                draggble: true,
                resizable: true,
                movable: true,
                keyboard: true,
                title: false,
                modalWidth: 400,
                modalHeight: 400,
                ratioThreshold: 0.01,
                minRatio: 0.1,
                maxRatio: 16,
                headToolbar: ['maximize', 'close'],
                footToolbar: ['zoomIn', 'zoomOut', 'prev', 'fullscreen', 'next', 'actualSize', 'rotateRight'],
                initMaximized: true,
                initAnimation: true,
                zIndex: 9999,
                progressiveLoading: true
            };

        // looping to create images array
        $('[data-gallery=manual]').each(function () {
            let src = $(this).attr('src');
            items.push({
                src: src
            });
        });

        new PhotoViewer(items, options); // 参考文档:http://www.jq22.com/yanshi20878

    });

}