package com.keqi.tdengine.service;

import com.keqi.tdengine.domain.param.CreateDbParam;
import com.keqi.tdengine.mapper.TDengineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * TDengineService
 *
 * @author keqi
 */
@Service
public class TDengineService {

    @Autowired
    private TDengineMapper tDengineMapper;

    public List<Map<String, Object>> showDatabases() {
        return tDengineMapper.showDatabases();
    }

    public void createDb(CreateDbParam param) {
        tDengineMapper.createDb(param);
    }

    public void deleteDb(String dbName) {
        tDengineMapper.deleteDb(dbName);
    }
}
