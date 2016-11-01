<!DOCTYPE html>
<html lang="en">
<head>
<#include "include/include.ftl">
    <title>Vue Demo</title>
</head>
<body id="app">
<div style="visibility: hidden">
    <label id="numCircuitGroupID" value="${numCircuitGroupID}"/>
</div>
<div id="main-view">
    <div id="main-view" >
        <div id="svgid"></div>
    </div>
</div>
</div>
</body>
</html>
<script  type="text/javascript">
    $(document).ready(function () {
        var data = {};
        data["numCircuitGroupID"] = $("#numCircuitGroupID").attr("value");
        $.post("/FirstPage/getXML", {data: JSON.stringify(data)}, function (result) {
            console.log(result.xml);
            $.quake.comp.commdraw.draw(result.xml);
            $.quake.comp.commdraw.initmeterdata('svgid');
        }, 'json');
    });
</script>


