package com.dnp.web.service;

import com.dnp.web.mapper.GetDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/19.
 */
@Component
public class GetDataService {
    @Autowired
    GetDataMapper getDataMapper;
    public List<HashMap> getAll(HashMap<String, Object> data) {
        return getDataMapper.getAll(data);
    }
    public List<HashMap> listAll(HashMap<String, Object> data) {
        return getDataMapper.listAll(data);
    }

    public int getAllCount(HashMap<String, Object> data) {
        return getDataMapper.getAllCount(data);
    }



    public List<HashMap> getAllByDateRangeAndnumCircuitGroupIDs(HashMap<String, Object> data) {
        return getDataMapper.getAllByDateRangeAndnumCircuitGroupIDs(data);
    }
    public List<HashMap> listAllByDateRangeAndnumCircuitGroupIDs(HashMap<String, Object> data) {
        return getDataMapper.listAllByDateRangeAndnumCircuitGroupIDs(data);
    }

    public int getAllCountByDateRangeAndnumCircuitGroupIDs(HashMap<String, Object> data) {
        return getDataMapper.getAllCountByDateRangeAndnumCircuitGroupIDs(data);
    }
}
