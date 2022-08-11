package tech.taoq.modbus;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 统一存储 AbstractModbusMasterTCP 对象, 并提供断线重连能力
 *
 * @author keqi
 */
@Lazy
@Component
public class ReconnectModbusMasterTCP {

    private static final Logger log = LoggerFactory.getLogger(AbstractModbusMasterTCP.class);

    private final Map<String, List<AbstractModbusMasterTCP>> MODBUS_MASTER_TCP_MAP = new HashMap<>();
    private static final ScheduledExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    @PostConstruct
    public void init() {
        log.info("ReconnectModbusMasterTCP init start");
        EXECUTOR_SERVICE.scheduleAtFixedRate(() -> {
            // 每隔 5 秒钟检测 ModbusTCP 连接是否断线，若断线，要主动重连
            MODBUS_MASTER_TCP_MAP.forEach((type, abstractModbusMasterTCPList) -> abstractModbusMasterTCPList.forEach(abstractModbusMasterTCP -> {
                if (!abstractModbusMasterTCP.isConnected()) {
                    try {
                        log.info("reconnect modbus slave device {} starting", abstractModbusMasterTCP.getDeviceName());
                        abstractModbusMasterTCP.connect();
                        log.info("reconnect modbus slave device {} success", abstractModbusMasterTCP.getDeviceName());
                    } catch (ModbusIOException e) {
                        log.error("reconnect modbus slave device {} failure", abstractModbusMasterTCP.getDeviceName());
                    }
                }
            }));
        }, 60, 5, TimeUnit.SECONDS);
        log.info("ReconnectModbusMasterTCP init end");
    }

    /**
     * 将 AbstractModbusMasterTCP 实现类对象统一进行保存
     *
     * @param type      类型
     * @param masterTCP AbstractModbusMasterTCP 连接对象
     */
    public void addMaster(String type, AbstractModbusMasterTCP masterTCP) {
        List<AbstractModbusMasterTCP> list = MODBUS_MASTER_TCP_MAP.computeIfAbsent(type, k -> new ArrayList<>());

        for (AbstractModbusMasterTCP abstractModbusMasterTCP : list) {
            if (Objects.equals(masterTCP.getDeviceName(), abstractModbusMasterTCP.getDeviceName())) {
                throw new ModbusTCPException("Under the same type, deviceName cannot be the same");
            }
        }

        list.add(masterTCP);
        log.info("ReconnectModbusMasterTCP addMaster type {}, name {}", type, masterTCP.getDeviceName());
    }

    /**
     * 移除 AbstractModbusMasterTCP 实现类对象
     *
     * @param type      类型
     * @param masterTCP AbstractModbusMasterTCP 连接对象
     */
    public void removeMaster(String type, AbstractModbusMasterTCP masterTCP) {
        this.removeMaster(type, masterTCP.getDeviceName());
    }

    /**
     * 移除 AbstractModbusMasterTCP 实现类对象
     *
     * @param type       类型
     * @param deviceName 连接对象名称
     */
    public void removeMaster(String type, String deviceName) {
        List<AbstractModbusMasterTCP> list = MODBUS_MASTER_TCP_MAP.get(type);
        if (list != null && list.size() > 0) {
            list.removeIf(abstractModbusMasterTCP -> {
                boolean result = Objects.equals(deviceName, abstractModbusMasterTCP.getDeviceName());
                if (result) {
                    log.info("ReconnectModbusMasterTCP removeMaster type {} by deviceName, name {}", type, deviceName);
                }
                return result;
            });
        }
    }

    /**
     * 根据类型和名称获取 AbstractModbusMasterTCP 实现类对象
     *
     * @param type       类型
     * @param deviceName 连接对象名称
     */
    public AbstractModbusMasterTCP getMaster(String type, String deviceName) {
        List<AbstractModbusMasterTCP> list = MODBUS_MASTER_TCP_MAP.get(type);
        if (list != null && list.size() > 0) {
            for (AbstractModbusMasterTCP masterTCP : list) {
                if (Objects.equals(deviceName, masterTCP.getDeviceName())) {
                    return masterTCP;
                }
            }
        }
        return null;
    }
}
