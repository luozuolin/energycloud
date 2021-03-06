<!DOCTYPE html>
<html lang="en">
<head>
<#include "include.ftl">
    <title>Main Page</title>
</head>
<body id="app"  >
<#include "menu.ftl">
<div style="float:left;padding: 10px"  id="divtable">
    <table id="tableedit" data-page-length='10' style="width: 100%"  cellspacing="0"  class=" datatable hover" ></table>
</div>
</body>
</html>
<script>
    var   table;
    //SELECT   r.numRefID,r.numFunctionID,f.varFunctionCode,r.numFunctionUserGroupID,g.varFunctionUserGroupName
    $(document).ready(function () {
        $(document).foundation();
        //u.numUserID,u.varUserName,u.varLoginName,u.datCreate,u.numOrganizeID,o.varOrganizeName,u.numPositionID,p.varPositionName
        table=new $.quake.comp.datatables({
            id:"tableedit",
            columns:[
                { data: "numRefID",text:"numRefID" ,"orderable": true},
                { data: "numFunctionID",text:"功能编号",visible:false ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/WD_Function/listAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numFunctionID + '">' + result[i].varFunctionCode + '</option>';
                            }
                            var id="numFunctionID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }
                }},
                { data: "varFunctionCode",text:"功能编号"},
                { data: "numFunctionUserGroupID",text:"功能用户组编号" ,visible:false ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        $.post("/FunctionUserGroupInfos/listAll" , null, function (result) {
                            var data = '<option value="-1">-请选择-</option>';
                            for (var i = 0; i < result.length; i++) {
                                data += '<option value="' + result[i].numFunctionUserGroupID + '">' + result[i].varFunctionUserGroupName + '</option>';
                            }
                            var id="numFunctionUserGroupID";
                            $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                            if(datas!=null) {$("#"+id).val(datas);}
                            $("#"+id).select2();
                        },'json');
                    }

                }},

                { data: "varFunctionUserGroupName",text:"功能用户组编号"},
                { data: "numFunctionCode",text:"功能码" ,edit:{
                    type:"select",
                    init:function(obj,datas)
                    {
                        var data = '<option value="0">-默认权限-</option><option value="1">可进入</option><option value="2">只读</option><option value="-1">可写</option>';
                        var id="numFunctionCode";
                        $("#"+id).parent().html('<select class="form-control" id="'+id+'">'+data+'</select>');
                        if(datas!=null) {$("#"+id).val(datas);}
                        $("#"+id).select2();
                    }
                }}
            ],
            dataurl:"/WD_FuncUGRefFunc/read",
            //添加删除修改所在的页面
            updateurl:"/WD_FuncUGRefFunc/edit/",
            selectstyle:"multi",
            "data":function(){
                return {name:$("#playername").val(),sportid:$("#sport").val()}
            }
        }).initdata();
    });
</script>
