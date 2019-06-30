<!DOCTYPE html>
<meta charset="utf-8">
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
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://d3js.org/d3.v3.min.js"></script>
<script src="/tree/country.js"></script>
<body>
<div id="tree-container"></div>
</body>
</html>