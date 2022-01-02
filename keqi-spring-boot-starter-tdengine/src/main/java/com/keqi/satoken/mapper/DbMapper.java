package com.keqi.satoken.mapper;

import com.keqi.satoken.domain.param.CreateDbParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface DbMapper {

    @Update("create database if not exists #{dbName} keep ${keep} days ${days} update #{update}")
    void createDb(CreateDbParam param);

    @Delete("drop database if exists #{dbName}")
    void deleteDb();

    @Select("show databases;")
    List<Map<String, Object>> showDatabases();


}
