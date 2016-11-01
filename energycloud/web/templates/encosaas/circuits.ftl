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
                <h2>回路查看</h2>
                <div class="space" style="height: calc(100% - 80px)">
                    <div class="row" style="margin-top:10px;margin-left:10px;margin-right:10px">
                        <div class="small-1 columns">
                            回路名称：
                        </div>
                        <div class="small-3 columns">
                            <input type="text" id="varCircuit" >
                        </div>
                        <div class="small-4 columns">
                            <input type="button" value="查询" class="button" id="btnsearch">
                        </div>

                        <div  style="margin-top:10px;margin-left:10px;margin-right:10px">
                            <table id="tableedit" data-page-length='10' style="width: 130%"  cellspacing="0"  class=" datatable hover" ></table>
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
        //u.numUserID,u.varUserName,u.varLoginName,u.datCreate,u.numOrganizeID,o.varOrganizeName,u.numPositionID,p.varPositionName
        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "numCircuitID",text:"ID",visible:false},

                { data: "varCircuitName",text:"回路名称" ,edit:{
                    type:"text"
                }},
                { data: "varID",text:"变量值" ,edit:{
                    type:"text"
                }},
                { data: "numOrganizeID",text:"上级组织名称",visible:false ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_Organize/OrganizelistAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numOrganizeID + '">' + result[i].varOrganizeName + '</option>';
                            }
                            var id="numOrganizeID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},

                { data: "varOrganizeName",text:"上级组织名称"},
                {
                    data: null,
                    text:'分时电量',
                    orderable:false,
                    defaultContent: '<a   onclick="DayEnergyConsumption(this);">查看当日分时电量</a>'
                },
                {
                    data: null,
                    text:'当月电量',
                    orderable:false,
                    defaultContent: '<a   onclick="monthEnergyConsumption(this);">查看当月能耗</a>'
                }


            ],
            dataurl:"/wd_circuitInfos/read",
            //添加删除修改所在的页面
            updateurl:"/wd_circuitInfos/edit/",
            selectstyle:"multi",
            "data":function(){
                return {varCircuitName:$("#varCircuit").val().toString()}

            }
        }).initdata();
    });
    $("#btnsearch").click(function(){

        table.reloadtable();
    })

</script>
