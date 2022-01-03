package com.keqi.tdengine.mapper;

import com.keqi.tdengine.domain.param.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * TDengineMapper
 *
 * @author keqi
 */
public interface TDengineMapper {

    /**
     * 如果指定的数据库不存在，则创建它
     *
     * @param param param
     */
    @Update("create database if not exists #{dbName} keep ${keep} days ${days} update #{update}")
    void createDb(CreateDbParam param);

    /**
     * 删除指定的数据库
     *
     * @param dbName dbName
     */
    @Delete("drop database if exists #{dbName}")
    void deleteDb(String dbName);

    /**
     * 修改数据库记录保存时长
     *
     * @param param param
     */
    @Update("alter database #{dbName} keep #{keep}")
    void updateDbKeep(UpdateDbKeepParam param);

    /**
     * 查询 TDengine 中所有的数据库
     *
     * @return r
     */
    @Select("show databases")
    List<Map<String, Object>> showDatabases();

    /**
     * 创建超级表
     */
    void createStable(CreateStableParam param);

    /**
     * 删除超级表
     *
     * @param sTableName sTableName
     */
    @Update("drop stable if exists #{dbName}.#{sTableName}")
    void deleteStable(@Param("dbName") String dbName, @Param("sTableName") String sTableName);

    /**
     * 超级表增加列
     *
     * @param param param
     */
    @Update("alter stable #{dbName}.#{sTableName} add column #{fieldName} #{fieldType}")
    void addColumn(AddColumnParam param);

    /**
     * 超级表删除列
     *
     * @param param param
     */
    @Update("alter stable #{dbName}.#{sTableName} drop column #{fieldName}")
    void dropColumn(DropColumnParam param);

    /**
     * 修改超级表列宽
     *
     * @param param param
     */
    @Update("alter stable #{dbName}.#{sTableName} modify column #{fieldName} #{fieldType}(#{length})")
    void alterColumn(AlterColumnParam param);

    /**
     * 超级表增加标签
     *
     * @param param param
     */
    @Update("alter stable #{dbName}.#{sTableName} add tag #{tagName} #{tagType}")
    void addTag(AddTagParam param);

    /**
     * 超级表删除标签
     *
     * @param param param
     */
    @Update("alter stable #{dbName}.#{sTableName} drop tag #{tagName}")
    void dropTag(DropColumnParam param);

    /**
     * 修改超级表标签宽
     *
     * @param param param
     */
    @Update("alter stable #{dbName}.#{sTableName} modify tag #{tagName} #{tagType}(#{length})")
    void alterTag(AlterTagParam param);

    /**
     * 修改子表标签值
     *
     * @param param param
     */
    @Update("alter table #{dbName}.#{tableName} set tag #{tagName}=#{tagValue}")
    void alterTagValue(AlterTagValueParam param);

    /**
     * 以超级表为模板，创建数据表
     *
     * @param param param
     */
    void createTable(CreateTableParam param);

    /**
     * 删除指定数据库中的指定数据表
     *
     * @param dbName    dbName
     * @param tableName tableName
     */
    @Update("drop table if exists #{dbName}.#{tableName}")
    void deleteTable(String dbName, String tableName);
}
