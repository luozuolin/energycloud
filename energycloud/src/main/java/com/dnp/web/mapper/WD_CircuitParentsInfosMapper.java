package com.dnp.web.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luozl on 2016/9/8.
 */
@Component
public interface WD_CircuitParentsInfosMapper {
    @Select("SELECT cp.numRefID,cp.datStart,cp.datEnd,c1.numCircuitID,c1.varCircuitName,c2.numCircuitID AS numPCircuitID,c2.varCircuitName AS varPCircuitName\n" +
            "            FROM WD_CircuitParents cp\n" +
            "            LEFT JOIN WD_Circuit c1 ON cp.numCircuitID=c1.numCircuitID\n" +
            "            LEFT JOIN WD_Circuit c2 ON cp.numPCircuitID=c2.numCircuitID\n" +
            "            ORDER BY cp.numRefID DESC ${sql}")
    List<HashMap> getAll(HashMap<String, Object> data);

    @Select("SELECT  count(1) from WD_CircuitParents")
    int getAllCount(HashMap<String, Object> data);


    @Insert("insert WD_CircuitParents(numPCircuitID,datStart,datEnd,numCircuitID) values(#{numPCircuitID},#{datStart},#{datEnd},#{numCircuitID})")
    @Options(useGeneratedKeys = true, keyProperty = "numRefID", keyColumn = "numRefID")
    int insert(HashMap<String, Object> data);

    @Update("update WD_CircuitParents set datStart=#{datStart},datEnd=#{datEnd},numPCircuitID=#{numPCircuitID},numCircuitID=#{numCircuitID} where numRefID=#{numRefID}")
    int update(HashMap<String, Object> data);
    @Delete("delete from WD_CircuitParents where numRefID=#{numRefID}")
    int delete(HashMap<String, Object> data);


    @Select("select * from WD_CircuitParents")
   List<HashMap> listAll();



}
