package com.dnp.web.encosaas.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/10/13.
 */
@Component
public interface FirstPageMapper {
    @Select("select * from WD_Circuit")
    List<HashMap> getAll(HashMap<String,Object> data);
    @Select("SELECT DATE_FORMAT(datStatistics,'%Y-%m-%d') datStatistics ,SUM(numValue) total from WD_StatisticsCircuit WHERE datStatistics BETWEEN '2014-1-1' and '2014-1-12' and numCircuitID in(\n" +
            "SELECT  numCircuitID from WD_Circuit WHERE   numEnergyTypeID=2 and numCircuitID not in(SELECT  numCircuitID from WD_CircuitParents )) GROUP BY datStatistics;\n")
    List<HashMap> getMonth(HashMap<String,Object> data);
    @Select("select * from WD_Circuit")
    List<HashMap> getDay(HashMap<String,Object> data);
    @Select("SELECT  * from WD_Organize WHERE numPOrganizeID=#{numPOrganizeID}")
    List<HashMap> getStation(HashMap<String,Object> data);
    @Select("SELECT  *  from WD_Config WHERE numCircuitGroupID=#{numCircuitGroupID} and numConfigTypeID=2")
    List<HashMap> getXML(HashMap<String,Object> data);


    @Select("SELECT g.*,p.numPCircuitID,c.varID,c.varCircuitName from WD_CircuitRefGroup g left join WD_Circuit c on g.numCircuitID=c.numCircuitID left join  WD_CircuitParents p on g.numCircuitID=p.numCircuitID WHERE g.numCircuitGroupID=#{numCircuitGroupID}")
    List<HashMap> getCircuitsBynumCircuitGroupID(HashMap<String,Object> data);

}
