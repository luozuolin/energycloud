package com.dnp.web.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/19.
 */
@Component
public interface GetDataMapper {
    // {numCircuitGroupID:$("#numCircuitGroupID").val().toString(),start:$("#date-range200").val(),end:$("#date-range201").val()}
   // @Select("select DATE_FORMAT(a.datStatistics,'%Y-%m-%d') datStatistics ,g.varCircuitGroupName, sum(a.numValue) numvalue from WD_StatisticsCircuit a, WD_CircuitRefGroup b ,WD_CircuitGroup g where a.numCircuitID=b.numCircuitID and (b.numCircuitGroupID=16 or b.numCircuitGroupID=11 )  and b.numCircuitGroupID=g.numCircuitGroupID and a.datStatistics BETWEEN '2014-1-1' and '2014-2-2'  group by g.varCircuitGroupName, a.datStatistics ${sql}")
    @Select("select DATE_FORMAT(a.datStatistics,'%Y-%m-%d') datStatistics ,g.varCircuitGroupName, sum(a.numValue) numvalue from WD_StatisticsCircuit a, WD_CircuitRefGroup b ,WD_CircuitGroup g where a.numCircuitID=b.numCircuitID and b.numCircuitGroupID in(${numCircuitGroupID})  and b.numCircuitGroupID=g.numCircuitGroupID and a.datStatistics BETWEEN #{start} and #{end}  group by g.varCircuitGroupName, a.datStatistics ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);
   // @Select("select count(*) from (select DATE_FORMAT(a.datStatistics,'%Y-%m-%d') datStatistics ,g.varCircuitGroupName, sum(a.numValue) numvalue from WD_StatisticsCircuit a, WD_CircuitRefGroup b ,WD_CircuitGroup g where a.numCircuitID=b.numCircuitID and (b.numCircuitGroupID=16 or b.numCircuitGroupID=11 )  and b.numCircuitGroupID=g.numCircuitGroupID and a.datStatistics BETWEEN '2014-1-1' and '2014-2-2'  group by g.varCircuitGroupName, a.datStatistics) a ")
    @Select("select count(*) from (select DATE_FORMAT(a.datStatistics,'%Y-%m-%d') datStatistics ,g.varCircuitGroupName, sum(a.numValue) numvalue from WD_StatisticsCircuit a, WD_CircuitRefGroup b ,WD_CircuitGroup g where a.numCircuitID=b.numCircuitID and b.numCircuitGroupID in(${numCircuitGroupID}) and b.numCircuitGroupID=g.numCircuitGroupID and a.datStatistics BETWEEN #{start} and #{end}  group by g.varCircuitGroupName, a.datStatistics) a ")
    int getAllCount(HashMap<String, Object> data);


    @Select("select DATE_FORMAT(a.datStatistics,'%Y-%m-%d') datStatistics ,g.varCircuitGroupName, sum(a.numValue) numvalue from WD_StatisticsCircuit a, WD_CircuitRefGroup b ,WD_CircuitGroup g where a.numCircuitID=b.numCircuitID and b.numCircuitGroupID in(${numCircuitGroupID})  and b.numCircuitGroupID=g.numCircuitGroupID and a.datStatistics BETWEEN #{start} and #{end}  group by g.varCircuitGroupName, a.datStatistics ")
    List<HashMap> listAll(HashMap<String, Object> data);

    @Select("SELECT  DATE_FORMAT(s.datStatistics,'%Y-%m-%d') datStatistics,c.varCircuitName,s.numValue  from WD_StatisticsCircuit s left join WD_Circuit c on s.numCircuitID=c.numCircuitID WHERE s.numCircuitID in(${numCircuitID}) and s.datStatistics BETWEEN #{start} and #{end}  ORDER BY c.varCircuitName,datStatistics ${sql}")
    List<HashMap> getAllByDateRangeAndnumCircuitGroupIDs(HashMap<String, Object> data);
    // @Select("select count(*) from (select DATE_FORMAT(a.datStatistics,'%Y-%m-%d') datStatistics ,g.varCircuitGroupName, sum(a.numValue) numvalue from WD_StatisticsCircuit a, WD_CircuitRefGroup b ,WD_CircuitGroup g where a.numCircuitID=b.numCircuitID and (b.numCircuitGroupID=16 or b.numCircuitGroupID=11 )  and b.numCircuitGroupID=g.numCircuitGroupID and a.datStatistics BETWEEN '2014-1-1' and '2014-2-2'  group by g.varCircuitGroupName, a.datStatistics) a ")
    @Select("select count(*) from (SELECT  DATE_FORMAT(s.datStatistics,'%Y-%m-%d') datStatistics,c.varCircuitName,s.numValue  from WD_StatisticsCircuit s left join WD_Circuit c on s.numCircuitID=c.numCircuitID WHERE s.numCircuitID in(${numCircuitID}) and s.datStatistics BETWEEN #{start} and #{end}  ORDER BY c.varCircuitName,datStatistics) a ")
    int getAllCountByDateRangeAndnumCircuitGroupIDs(HashMap<String, Object> data);
    @Select("SELECT  DATE_FORMAT(s.datStatistics,'%Y-%m-%d') datStatistics,c.varCircuitName,s.numValue  from WD_StatisticsCircuit s left join WD_Circuit c on s.numCircuitID=c.numCircuitID WHERE s.numCircuitID in(${numCircuitID}) and s.datStatistics BETWEEN #{start} and #{end}  ORDER BY c.varCircuitName,datStatistics;")
    List<HashMap> listAllByDateRangeAndnumCircuitGroupIDs(HashMap<String, Object> data);

}
