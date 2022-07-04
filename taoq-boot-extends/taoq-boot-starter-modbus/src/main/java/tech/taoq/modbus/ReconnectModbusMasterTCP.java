package tech.taoq.modbus;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * ModbusTCP Master 断线重连
 *
 * @author keqi
 */
@Slf4j
@Lazy
@Component
public class ReconnectModbusMasterTCP {

    private final Map<String, List<AbstractModbusMasterTCP>> MODBUS_MASTER_TCP_MAP = new HashMap<>();
    private static final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @PostConstruct
    public void init() {
        log.info("ReconnectModbusMasterTCP init start");
        executorService.scheduleAtFixedRate(() -> {
            // 每隔 5 秒钟检测 ModbusTCP 连接是否断线，若断线，要主动重连
            MODBUS_MASTER_TCP_MAP.forEach((s, abstractModbusMasterTCPS) -> abstractModbusMasterTCPS.forEach(odbmusMasterTCP -> {
                if (!odbmusMasterTCP.isConnected()) {
                    try {
                        log.info("reconnect modbus slave device {} starting", odbmusMasterTCP.getDeviceName());
                        odbmusMasterTCP.connect();
                        log.info("reconnect modbus slave device {} success", odbmusMasterTCP.getDeviceName());
                    } catch (ModbusIOException e) {
                        log.error("reconnect modbus slave device {} failure", odbmusMasterTCP.getDeviceName());
                    }
                }
            }));
        }, 60, 5, TimeUnit.SECONDS);
        log.info("ReconnectModbusMasterTCP init end");
    }

    public void addMaster(String type, AbstractModbusMasterTCP masterTCP) {
        List<AbstractModbusMasterTCP> list = MODBUS_MASTER_TCP_MAP.get(type);
        if (list == null || list.size() == 0) {
            list = new ArrayList<>();
            MODBUS_MASTER_TCP_MAP.put(type, list);
        }
        list.add(masterTCP);
        log.info("ReconnectModbusMasterTCP addMaster type {}, name {}", type, masterTCP.getDeviceName());
    }

    public void removeMaster(String type, AbstractModbusMasterTCP masterTCP) {
        List<AbstractModbusMasterTCP> list = MODBUS_MASTER_TCP_MAP.get(type);
        if (list != null && list.size() > 0) {
            list.removeIf(abstractModbusMasterTCP -> {
                boolean result = Objects.equals(masterTCP.getDeviceName(), abstractModbusMasterTCP.getDeviceName());
                if (result) {
                    log.info("ReconnectModbusMasterTCP removeMaster type {}, name {}", type, masterTCP.getDeviceName());
                }
                return result;
            });
        }
    }

    public AbstractModbusMasterTCP getMaster(String type, String name) {
        List<AbstractModbusMasterTCP> list = MODBUS_MASTER_TCP_MAP.get(type);
        if (list != null && list.size() > 0) {
            for (AbstractModbusMasterTCP masterTCP : list) {
                if (Objects.equals(name, masterTCP.getDeviceName())) {
                    return masterTCP;
                }
            }
        }
        return null;
    }
}
