package com.dnp.web.service;

import com.dnp.web.mapper.GetDataMapper;
import com.dnp.web.mapper.WD_StatisticsCircuitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/19.
 */
@Component
public class WD_StatisticsCircuitService {
    @Autowired
    WD_StatisticsCircuitMapper wd_statisticsCircuitMapper;
    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_statisticsCircuitMapper.getAll(data);
    }
    public List<HashMap> listAll(HashMap<String, Object> data) {
        return wd_statisticsCircuitMapper.listAll(data);
    }

    public int getAllCount(HashMap<String, Object> data) {
        return wd_statisticsCircuitMapper.getAllCount(data);
    }
}
