package com.dnp.web.service;

import com.dnp.web.mapper.WD_CircuitParentsInfosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public class WD_CircuitParentsInfosService {
    @Autowired
    WD_CircuitParentsInfosMapper wd_circuitParentsInfosMapper;




    public List<HashMap> getAll(HashMap<String, Object> data) {
        return wd_circuitParentsInfosMapper.getAll(data);
    }

    public int getAllCount(HashMap<String, Object> data) {
        return wd_circuitParentsInfosMapper.getAllCount(data);
    }

    public int update(HashMap<String, Object> data, String updateMethod) {
        if (updateMethod.equals("add")) {
            return wd_circuitParentsInfosMapper.insert(data);
        } else if (updateMethod.equals("update")) {
            return wd_circuitParentsInfosMapper.update(data);
        } else if (updateMethod.equals("dele")) {
            return wd_circuitParentsInfosMapper.delete(data);
        }
        return 1;
    }
    public List<HashMap> listAll()
    {
        return wd_circuitParentsInfosMapper.listAll();
    }

}
