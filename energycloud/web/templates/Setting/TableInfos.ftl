<!DOCTYPE html>
<html lang="en">
<head>
<#include "../encosaas/include/include.ftl">
</head>
<body>
<#include "../encosaas/include/header.ftl">
<div class="main">
<#include "../encosaas/include/left.ftl">
    <div class="main_right"  >
        <div class="contentwrap">
            <div class="contentBox">
                <h2>系统拓扑图</h2>
                <div class="space" style="height: calc(100% - 60px);">
                    <div  style="margin-top:10px;margin-left:10px;margin-right:10px">
                        <table id="tableedit" data-page-length='10' style="width: 100%"  cellspacing="0"  class=" datatable hover" ></table>
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
        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "numTableID",text:"ID", visible:false},

                { data: "varIP",text:"IP"  ,edit:{
                    type:"text"
                }},
                { data: "numPort",text:"端口"  ,edit:{
                    type:"text"
                }},
                { data: "numDeviceAddress",text:"装置地点"  ,edit:{
                    type:"text"
                }},
                { data: "numBandRate",text:"比率"  ,edit:{
                    type:"text"
                }},
                { data: "varStatus",text:"正常"},

                { data: "numEnergyTypeId",text:"能耗类型",visible:false,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_EnergyType/listAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numEnergyTypeId + '">' + result[i].varEnergyTypeName + '</option>';
                            }
                            var id="numEnergyTypeId";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},

                { data: "varEnergyTypeName",text:"能耗名称"},
                { data: "varCom",text:"接口"  ,edit:{
                    type:"text"
                }},
                { data: "numPOrganizeID",text:"组织名称",visible:false ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_Organize/listAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numPOrganizeID + '">' + result[i].varPOrganizeName + '</option>';
                            }
                            var id="numPOrganizeID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }

                }},
                { data: "varPOrganizeName",text:"组织名称"},
                { data: "varXML",text:"XML",edit:{
                    type:"text"
                }}

            ],
            dataurl:"/WD_TableInfos/read",
            //添加删除修改所在的页面
            updateurl:"/WD_TableInfos/edit/",
            selectstyle:"multi",
            "data":function(){
                return {name:$("#playername").val(),sportid:$("#sport").val()}
            }
        }).initdata();
    });

</script>


