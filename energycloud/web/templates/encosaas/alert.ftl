<!DOCTYPE html>
<html>
<head>
<#include "include/include.ftl">
</head>
<body>
<#include "include/header.ftl">
<div class="main">
<#include "include/left.ftl">
    <div class="main_right"  >
        <div class="contentwrap">
            <div class="contentBox">
                <h2>数据预警</h2>
                <div class="space" style="height: calc(100% - 80px)">
                    <div class="row" style="margin-top:10px;margin-left:10px;margin-right:10px">
                        <div class="small-2 columns">
                            选择回路
                        </div>
                        <div class="small-10 columns">
                            <input type="text" id="numCircuitID" >
                        </div>
                        <div class="small-2 columns">
                            时间区间
                        </div>
                        <div class="small-10 columns">
                            <div id="daterange" ></div>
                        </div>
                        <div class="small-4 columns">
                            <div>
                                <button type="button" style="width: 100px"  class="button" id="btnsearch">查询</button>
                            </div>
                        </div>


                        <div style="float:left;"  id="divtable">
                            <table id="tableedit" data-page-length='10' style="width: 132%"  cellspacing="0"  class=" datatable hover" ></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<script>
    var   table;
    $(document).ready(function () {
        $(document).foundation();
        // numCircuitID,varCircuitName
        $.post("/wd_circuitInfos/getCircuitsBynumUserID" , null, function (result) {
            var data = '<option value="-1">-请选择-</option>';
            for (var i = 0; i < result.length; i++) {
                data += '<option value="' + result[i].numCircuitID + '">' + result[i].varCircuitName + '</option>';
            }
            var id="numCircuitID";
            $("#"+id).parent().html('<select  id="'+id+'">'+data+'</select>');
            $("#"+id).val(null);
            $("#"+id).select2({multiple:"multiple"});
        },'json');
        $.quake.comp.daterangepicker({
            id: "daterange",
            type: "daterange",
            start: "date-range200",
            end: "date-range201"
        });
        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "varCircuitName",text:"回路名称"},
                { data: "datStart",text:"预警发生时间"},
                { data: "varMessage",text:"预警详细内容"},

            ],
            dataurl:"/wd_alarmLog/read",
            //reloadtable时需要传递参数
            "data":function(){
                return { numCircuitID:$("#numCircuitID").val()==null?"":$("#numCircuitID").val().toString(),start:$("#date-range200").val()==""?"2014-1-1":$("#date-range200").val(),end:$("#date-range201").val()==""?"2014-12-1":$("#date-range201").val()}

            }
        }).initdata();

    });
         $("#btnsearch").click(function(){
            // getchartdata();
             table.reloadtable();
    });
</script>