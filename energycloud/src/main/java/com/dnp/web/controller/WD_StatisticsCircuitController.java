package com.dnp.web.controller;

import com.dnp.util.DataTableService;
import com.dnp.util.JsonUtil;
import com.dnp.web.service.GetDataService;
import com.dnp.web.service.WD_StatisticsCircuitService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
* @Auther Tao 
* @date:2016/10/18 15:13
*/
@Controller
@RequestMapping("/statisticsCircuit")
public class WD_StatisticsCircuitController {
    @Autowired
    WD_StatisticsCircuitService wd_statisticsCircuitService;
    @RequestMapping(value = "/getCircuitDatas", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String read(HttpServletRequest request, String data) {
        DataTableService s=new DataTableService(request);
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        object.put("sql",s.getorderandlimitsql());
        int count=wd_statisticsCircuitService.getAllCount(object);
        List<HashMap> datas=wd_statisticsCircuitService.getAll(object);
        return JsonUtil.toJson(s.getDatas(datas,count));
    }
    @RequestMapping(value = "/listAll", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String listAll(HttpServletRequest request, String data) {
        HashMap<String,Object> object = JsonUtil.JsonObjectToHashMap(new JSONObject(data==null?"{}":data));
        List<HashMap> datas=wd_statisticsCircuitService.listAll(object);
        return JsonUtil.toJson(datas);
    }
}
