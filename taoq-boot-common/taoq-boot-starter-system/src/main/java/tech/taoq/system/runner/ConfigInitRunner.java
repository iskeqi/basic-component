package tech.taoq.system.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.taoq.system.domain.db.ConfigDO;
import tech.taoq.system.mapper.ConfigMapper;
import tech.taoq.system.service.impl.ConfigServiceImpl;

import java.util.List;

@Slf4j
@Component
public class ConfigInitRunner implements CommandLineRunner {

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public void run(String... args) {
        try {
            List<ConfigDO> configDOS = configMapper.selectList(null);
            configDOS.forEach(configDO -> ConfigServiceImpl.CONFIG_MAP.put(configDO.getConfigKey(), configDO));
        } catch (Throwable e) {
            log.error("ConfigInitRunner init failure", e);
        }
    }
}
