package com.keqi.tbt.schedule;

import com.keqi.tbt.service.TaoqDoor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.taoq.modbus.AbstractModbusMasterTCP;
import tech.taoq.modbus.ModbusMasterTcpPool;
import tech.taoq.modbus.ModbusTCPException;

@Slf4j
@Component
public class DoorConnect {

    @Autowired
    private ModbusMasterTcpPool modbusMasterTcpPool;

    /**
     * 自定义实现断线主动重连
     */
    // @Scheduled(initialDelay = 3000, fixedDelay = 50)
    public void doorConnect() {
        String type = "DOOR";
        String deviceName = "F21C1092";
        String ip = "127.0.0.1";
        int port = 502;

        AbstractModbusMasterTCP master = modbusMasterTcpPool.getMaster(type, deviceName);
        if (master == null || !master.connected()) {
            TaoqDoor taoqDoor = null;
            try {
                log.error("开始建立连接,设备 {} ,IP {} ,端口 {} ", deviceName, ip, port);
                taoqDoor = new TaoqDoor(ip, 502, deviceName);
            } catch (ModbusTCPException e) {
                log.error("连接建立失败,设备 {} ,IP {} ,端口 {} ", deviceName, ip, port);
            }

            if (taoqDoor != null) {
                modbusMasterTcpPool.addMaster(type, taoqDoor);
            }
        } else {
            TaoqDoor taoqDoor = (TaoqDoor) master;

            taoqDoor.open();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.error("睡眠时发生了中断");
            }

            log.info("设备 {} ,开门状态 {}", deviceName, taoqDoor.isOpen());
        }
    }
}
