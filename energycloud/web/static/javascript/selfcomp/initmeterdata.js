/**
 * Created by luozl on 2016/8/10.
 */
//在tooltip中显示仪表的ia,ib,ic,ua,ub,uc
$.quake.comp.commdraw.initmeterdata=function(svgid){
    $("#"+svgid).contextmenu({
        delegate: "image",
        menu: [
            {title: "当日分时数据", cmd: "day", uiIcon: "ui-icon-copy"},
            {title: "当月每日数据", cmd: "month", uiIcon: "ui-icon-copy"}
        ],
        select: function(event, ui) {
            alert("select " + ui.cmd + " on :" + $(ui.target).attr("tag"));
        }
    });
    $("#"+svgid+" image").each(function(key, value) {
        if($(this).attr("tag")!="") {
            var data=jQuery.parseJSON($(this).attr("tag"));
            if(data["Tag"]=="meter" && data["ID"]!="") {
                //添加右键菜单
                $(this).click(function(){
                            $.get("/getdata?url=http://192.168.1.200:5000/ShowTable?name=" + data["ID"], null, function (result) {
                                var str = "电表：" + result["name"] + "(" + result["tableType"] + ")<br>";
                                var len = 0;
                                for (var k in result.registerPools[0]) {
                                    if (len++ > 10)
                                        break;
                                    str += k + ":" + (CheckDateTime(result.registerPools[0][k]) ? result.registerPools[0][k] : parseFloat(result.registerPools[0][k]).toFixed(2)) + "<br>";
                                }
                                $.quake.comp.alert({type: "alert", infor: str});
                            }, "json");

                });
            }}
    });
    refreshmeterdata(svgid);
    setInterval('refreshmeterdata('+svgid+')',60000); //指定1分钟刷新一次
};
function CheckDateTime(str){
    var reg=/^(\d+)-(\d{1,2})-(\d{1,2})(\s+)(\d{1,2}):(\d{1,2}):(\d{1,2})$/;
    var r=str.match(reg);
    if(r==null) return false;
    r[2]=r[2]-1;
    var d= new Date(r[1],r[2],r[3],r[4],r[5],r[6]);
    if(d.getFullYear()!=r[1]) return false;
    if(d.getMonth()!=r[2]) return false;
    if(d.getDate()!=r[3]) return false;
    if(d.getHours()!=r[4]) return false;
    if(d.getMinutes()!=r[5]) return false;
    if(d.getSeconds()!=r[6]) return false;
    return true;
}
function refreshmeterdata(svgid)
{
    //  alert(svgid);
    $("foreignObject[tag!='']").each(function() {
        //刷新仪表数据
        var data=jQuery.parseJSON($(this).attr("tag"));
        if(data["Tag"]=="meter" && data["ID"]!="") {
            var that=this;
            $.get("/getdata?url=http://192.168.1.200:5000/ShowTable?name=" + data["ID"], {obj:$(that).attr("id")}, function (result) {
              //  var str =  result["name"] + "(" + result["tableType"] + ")<br/>";
                if(result.registerPools!=null) {
                    var str = "";
                    str += "ia:" + parseFloat(result.registerPools[0]['ia']).toFixed(2) + "<br/>";
                    str += "va:" + parseFloat(result.registerPools[0]['van']).toFixed(2) + "<br/>";
                    str += "p:" + parseFloat(result.registerPools[0]['p']).toFixed(2) + "<br/>";
                    str += "pw+:" + parseFloat(result.registerPools[0]['pw+']).toFixed(2) + "<br/>";
                    $($("#" + getQueryString(this.url, "obj")).children()).html(str);
                }
            }, "json");
        }
    });
}
function getQueryString(str,name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = str.substr(1).match(reg);
    if (r != null) return unescape(decodeURI(r[2])); return null;
}