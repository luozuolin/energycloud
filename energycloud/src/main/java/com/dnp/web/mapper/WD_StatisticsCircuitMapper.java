package com.dnp.web.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wode on 7/8/16.
 */
@Component
public interface WD_StatisticsCircuitMapper {
    @Select("SELECT sc.datStatistics,c.varCircuitName,sc.numValue,(CASE WHEN DATE_FORMAT(datStatistics,'yyyy')-1 || DATE_FORMAT(datStatistics,'MM') THEN numValue\n" +
            "ELSE 0 END) AS 'TBnumValue',\n" +
            "(CASE WHEN DATE_FORMAT(datStatistics,'yyyy')|| DATE_FORMAT(datStatistics,'MM')-1  THEN numValue ELSE 0 END) AS 'HBnumValue',\n" +
            "(CASE WHEN DATE_FORMAT(datStatistics,'yyyy')|| DATE_FORMAT(datStatistics,'MM')  THEN numValue ELSE 0 END)\n" +
            "\n" +
            "FROM WD_StatisticsCircuit sc\n" +
            "LEFT JOIN WD_Circuit c ON sc.numCircuitID=c.numCircuitID\n" +
            "GROUP BY sc.numCircuitID,sc.datStatistics ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);
    @Select("SELECT COUNT(*) FROM\n" +
            "           WD_StatisticsCircuit")
    int getAllCount(HashMap<String, Object> data);
    @Select("select DATE_FORMAT(a.datStatistics,'%Y-%m-%d') datStatistics ,b.varCircuitName,a.numValue\n" +
            "from WD_StatisticsCircuit a, WD_Circuit b \n" +
            "where a.numCircuitID=b.numCircuitID and b.numCircuitID in(${numCircuitID})   and a.datStatistics \n" +
            "BETWEEN #{start} and #{end}  group by b.varCircuitName, a.datStatistics ")
    List<HashMap> listAll(HashMap<String, Object> data);
}
