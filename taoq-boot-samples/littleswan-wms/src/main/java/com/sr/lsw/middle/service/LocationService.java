package com.sr.lsw.middle.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sr.lsw.middle.domain.db.LocationDO;
import com.sr.lsw.middle.mapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.taoq.mp.pojo.PageDto;
import tech.taoq.mp.pojo.PageParam;

@Service
public class LocationService {

    @Autowired
    private LocationMapper locationMapper;

    public void insert(LocationDO param) {
        locationMapper.insert(param);
    }

    public void deleteById(String id) {
        locationMapper.deleteById(id);
    }

    public void updateById(LocationDO param) {
        locationMapper.updateById(param);
    }

    public PageDto<LocationDO> page(PageParam<LocationDO> param) {
        Page<LocationDO> page = locationMapper.selectPage(param.toPage(), null);
        return PageDto.build(page);
    }
}
