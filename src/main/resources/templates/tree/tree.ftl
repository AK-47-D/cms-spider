<!DOCTYPE html>

<head>
    <meta http-equiv=content-type content=text/html;charset=utf-8>
    <meta http-equiv=X-UA-Compatible content=IE=Edge>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>iLove</title>
    <link rel="icon" href="/logo.png" type="image/x-icon"/>

    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?2213885fc35faef4a4e7afffef5e655c";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>

    <style type="text/css">

        .node {
            cursor: pointer;
        }

        .overlay{
            background-color:#fff;
        }

        .node circle {
            fill: #fff;
            stroke: steelblue;
            stroke-width: 1px;
        }

        .node text {
            font-size:12px;
            font-family:sans-serif;
        }

        .link {
            fill: none;
            stroke: #ccc;
            stroke-width: 2px;
        }

        .templink {
            fill: none;
            stroke: red;
            stroke-width: 3px;
        }

        .ghostCircle.show{
            display:block;
        }

        .ghostCircle, .activeDrag .ghostCircle{
            display: none;
        }

    </style>
    <script>
        var _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?865c05a59ea3663dff6be786917ba02b";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>

</head>

<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://d3js.org/d3.v3.min.js"></script>

<body>
<input hidden id="category" value="${category}">
<div id="tree-container"></div>

<script src="/tree/tree.js"></script>
</body>
</html>