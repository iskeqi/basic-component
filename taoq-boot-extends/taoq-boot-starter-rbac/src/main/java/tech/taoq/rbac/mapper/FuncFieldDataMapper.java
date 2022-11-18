package tech.taoq.rbac.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import tech.taoq.rbac.domain.param.FuncFieldDataPageParam;

import java.util.List;
import java.util.Map;

public interface FuncFieldDataMapper {

    void insert(@Param("menuPermiss") String menuPermiss, @Param("dataMap") Map<String, String> dataMap);

    void deleteById(@Param("menuPermiss") String menuPermiss, @Param("id") String id);

    void updateById(@Param("id") String id, @Param("menuPermiss") String menuPermiss, @Param("dataMap")
            Map<String, String> dataMap);

    Long count(FuncFieldDataPageParam param);

    @MapKey("id")
    List<Map<String, String>> page(FuncFieldDataPageParam param);

    void createTable(@Param("tableName") String tableName, @Param("tableNameComment") String tableNameComment);

    void addColumn(@Param("tableName") String tableName, @Param("columnName") String columnName,
                   @Param("columnNameComment") String columnNameComment);

    void dropColumn(@Param("tableName") String tableName, @Param("columnName") String columnName);
}