package tech.taoq.modbus;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 继承此类即可拥有使用 ModbusTCP 协议与硬件设备通信的能力
 *
 * @author keqi
 */
public abstract class AbstractModbusMasterTCP {

    private static final Logger log = LoggerFactory.getLogger(AbstractModbusMasterTCP.class);

    private ModbusMaster modbusMaster;
    private final int serverAddress = 1;

    protected String host;
    protected int port;
    protected String deviceName;

    protected String errorMsg = "ModbusTCP slave [deviceName] occurs exception";

    public AbstractModbusMasterTCP(String host, int port, String deviceName) throws ModbusTCPException {
        try {
            this.deviceName = deviceName == null ? host + ":" + port : deviceName;
            this.host = host;
            this.port = port;

            errorMsg = errorMsg.replace("deviceName", this.deviceName);

            TcpParameters tcpParameters = new TcpParameters();
            tcpParameters.setHost(InetAddress.getByName(host));
            tcpParameters.setKeepAlive(true);
            tcpParameters.setPort(port);

            modbusMaster = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);

            Modbus.setAutoIncrementTransactionId(true);

            if (!modbusMaster.isConnected()) {
                modbusMaster.connect();
            }
        } catch (UnknownHostException | ModbusIOException e) {
            handleException(e);
        }
    }

    /**
     * 读线圈
     *
     * @param startAddress startAddress
     * @param quantity     quantity
     * @return r
     * @throws ModbusTCPException e
     */
    final public boolean[] readCoils(int startAddress, int quantity) throws ModbusTCPException {
        try {
            return modbusMaster.readCoils(serverAddress, startAddress, quantity);
        } catch (ModbusProtocolException | ModbusNumberException | ModbusIOException e) {
            handleException(e);
        }
        return null;
    }

    /**
     * 读单个线圈
     *
     * @param startAddress startAddress
     * @return r
     * @throws ModbusTCPException e
     */
    final public boolean readCoils(int startAddress) throws ModbusTCPException {
        return Objects.requireNonNull(readCoils(startAddress, 1))[0];
    }

    /**
     * 读保持寄存器
     *
     * @param startAddress startAddress
     * @param quantity     quantity
     * @return r
     * @throws ModbusTCPException e
     */
    final public int[] readHoldingRegisters(int startAddress, int quantity) throws ModbusTCPException {
        try {
            return modbusMaster.readHoldingRegisters(serverAddress, startAddress, quantity);
        } catch (ModbusProtocolException | ModbusNumberException | ModbusIOException e) {
            handleException(e);
        }
        return null;
    }

    /**
     * 读单个保持寄存器
     *
     * @param startAddress startAddress
     * @return r
     * @throws ModbusTCPException e
     */
    final public int readHoldingRegisters(int startAddress) throws ModbusTCPException {
        return Objects.requireNonNull(readHoldingRegisters(startAddress, 1))[0];
    }

    /**
     * 读离散寄存器
     *
     * @param startAddress startAddress
     * @param quantity     quantity
     * @return r
     * @throws ModbusTCPException e
     */
    final public boolean[] readDiscreteInputs(int startAddress, int quantity) throws ModbusTCPException {
        try {
            return modbusMaster.readDiscreteInputs(serverAddress, startAddress, quantity);
        } catch (ModbusProtocolException | ModbusNumberException | ModbusIOException e) {
            handleException(e);
        }
        return null;
    }

    /**
     * 读单个离散寄存器
     *
     * @param startAddress startAddress
     * @return r
     * @throws ModbusTCPException e
     */
    final public boolean readDiscreteInputs(int startAddress) throws ModbusTCPException {
        return Objects.requireNonNull(readDiscreteInputs(startAddress, 1))[0];
    }

    /**
     * 读输入寄存器
     *
     * @param startAddress startAddress
     * @param quantity     quantity
     * @return r
     * @throws ModbusTCPException e
     */
    final public int[] readInputRegisters(int startAddress, int quantity) throws ModbusTCPException {
        try {
            return modbusMaster.readInputRegisters(serverAddress, startAddress, quantity);
        } catch (ModbusProtocolException | ModbusNumberException | ModbusIOException e) {
            handleException(e);
        }
        return null;
    }

    /**
     * 读单个输入寄存器
     *
     * @param startAddress startAddress
     * @return r
     * @throws ModbusTCPException e
     */
    final public int readInputRegisters(int startAddress) throws ModbusTCPException {
        return Objects.requireNonNull(readInputRegisters(startAddress, 1))[0];
    }

    /**
     * 写单个线圈
     *
     * @param startAddress startAddress
     * @param coil         coil
     * @throws ModbusTCPException e
     */
    final public void writeSingleCoil(int startAddress, boolean coil) throws ModbusTCPException {
        try {
            modbusMaster.writeSingleCoil(serverAddress, startAddress, coil);
        } catch (ModbusProtocolException | ModbusNumberException | ModbusIOException e) {
            handleException(e);
        }
    }

    /**
     * 写多个线圈
     *
     * @param startAddress startAddress
     * @param coils        coils
     * @throws ModbusTCPException e
     */
    final public void writeMultipleCoils(int startAddress, boolean[] coils) throws ModbusTCPException {
        try {
            modbusMaster.writeMultipleCoils(serverAddress, startAddress, coils);
        } catch (ModbusProtocolException | ModbusNumberException | ModbusIOException e) {
            handleException(e);
        }
    }

    /**
     * 写单个保持寄存器
     *
     * @param startAddress startAddress
     * @param register     register
     * @throws ModbusTCPException e
     */
    final public void writeSingleRegister(int startAddress, int register) throws ModbusTCPException {
        try {
            modbusMaster.writeSingleRegister(serverAddress, startAddress, register);
        } catch (ModbusProtocolException | ModbusNumberException | ModbusIOException e) {
            handleException(e);
        }
    }

    /**
     * 写多个保持寄存器
     *
     * @param startAddress startAddress
     * @param registers    registers
     * @throws ModbusTCPException e
     */
    final public void writeMultipleRegisters(int startAddress, int[] registers) throws ModbusTCPException {
        try {
            modbusMaster.writeMultipleRegisters(serverAddress, startAddress, registers);
        } catch (ModbusProtocolException | ModbusNumberException | ModbusIOException e) {
            handleException(e);
        }
    }

    /**
     * 判断连接是否正常
     *
     * @return true 正常 false 失败
     */
    final public boolean connected() {
        return modbusMaster.isConnected();
    }

    /**
     * 建立连接
     *
     * @throws ModbusIOException exception
     */
    final public void connect() throws ModbusIOException {
        modbusMaster.connect();
    }

    /**
     * 断开连接
     *
     * @throws ModbusIOException exception
     */
    final public void disconnect() throws ModbusIOException {
        modbusMaster.disconnect();
    }

    /**
     * 将 16 bit 的整数对应的二进制字符串转换成对应的 boolean 数组
     *
     * @param data data
     * @return boolean 数组
     */
    final public boolean[] parseShortToBitArray(short data) {
        return parseIntToBitArray(data, 16);
    }

    /**
     * 将 32 bit 的整数对应的二进制字符串转换成对应的 boolean 数组
     *
     * @param data data
     * @return boolean 数组
     */
    final public boolean[] parseIntToBitArray(int data) {
        return parseIntToBitArray(data, 32);
    }

    /**
     * 计算 16bit 的整数对应的 ASCII 编码的字符串表示
     *
     * @param param   十进制的 16bit 整数
     * @param reverse 是否需要反转[true:低8位在前,高8位在后 false:低8位在后,高8位在前]
     * @return 十进制对应的 ASCII 编码的字符串
     */
    final public String shortToString(short param, boolean reverse) {

        // [高八位数据]右移8位后,取 int 类型的最低8位,其它抛弃
        byte a = (byte) (param >> 8);

        // [低八位数据]取 int 类型的最低8位,其它抛弃
        byte b = (byte) param;

        // 拼接成字符串时,低8位的数据放到前面,高8位的数据放到后面
        byte[] bytes = reverse ? new byte[]{b, a} : new byte[]{a, b};
        return new String(bytes);
    }

    /**
     * 计算 ASCII 编码字符串对应的二进制数据的十进制表示
     *
     * @param param   ASCII 编码字符串
     * @param reverse 是否需要反转[true:低8位在前,高8位在后 false:低8位在后,高8位在前]
     * @return ASCII 编码的字符串对应的十进制整数
     */
    final public short stringToShort(String param, boolean reverse) {
        if (param.length() != 2) {
            throw new RuntimeException("本方法仅支持长度位2的字符串");
        }

        // 字符串转成对应的字节数组
        byte[] bytes = param.getBytes(StandardCharsets.UTF_8);

        // 字节转换成对应的二进制形式表示,不足8位的在前面进行补0
        String str1 = String.format("%08d", Integer.parseInt(Integer.toString(bytes[0], 2)));
        String str2 = String.format("%08d", Integer.parseInt(Integer.toString(bytes[1], 2)));

        // 16 个 bit 的二进制字符串转成对应的 int 类型整数,低8位放在前面,高8位放到后面
        return Short.parseShort(reverse ? str2 + str1 : str1 + str2, 2);
    }

    /**
     * 计算 32bit 的整数对应的 ASCII 编码的字符串表示
     *
     * @param param   十进制的 16bit 整数
     * @param reverse 是否需要反转[true:低8位在前,高8位在后 false:低8位在后,高8位在前]
     * @return 十进制对应的 ASCII 编码的字符串
     */
    final public String intToString(int param, boolean reverse) {

        // [从右开始,第1个八位数据]取 int 类型的最低8位,其它抛弃
        byte d = (byte) param;

        // [从右开始,第2个八位数据]右移8位后,取 int 类型的最低8位,其它抛弃
        byte c = (byte) (param >> 8);

        // [从右开始,第3个八位数据]右移16位后,取 int 类型的最低16位,其它抛弃
        byte b = (byte) (param >> 16);

        // [从右开始,第4个八位数据]右移24位后,取 int 类型的最低8位,其它抛弃
        byte a = (byte) (param >> 24);

        // 拼接成字符串时,低8位的数据放到前面,高8位的数据放到后面
        byte[] bytes = reverse ? new byte[]{d, c, b, a} : new byte[]{a, b, c, d};

        return new String(bytes);
    }

    /**
     * 计算 ASCII 编码字符串对应的二进制数据的十进制表示
     *
     * @param param   ASCII 编码字符串
     * @param reverse 是否需要反转[true:低8位在前,高8位在后 false:低8位在后,高8位在前]
     * @return ASCII 编码的字符串对应的十进制整数
     */
    final public int stringToInt(String param, boolean reverse) {
        if (param.length() != 4) {
            throw new RuntimeException("本方法仅支持长度位4的字符串");
        }

        // 字符串转成对应的字节数组
        byte[] bytes = param.getBytes(StandardCharsets.UTF_8);

        // 字节转换成对应的二进制形式表示,不足8位的在前面进行补0
        String str1 = String.format("%08d", Integer.parseInt(Integer.toString(bytes[0], 2)));
        String str2 = String.format("%08d", Integer.parseInt(Integer.toString(bytes[1], 2)));
        String str3 = String.format("%08d", Integer.parseInt(Integer.toString(bytes[2], 2)));
        String str4 = String.format("%08d", Integer.parseInt(Integer.toString(bytes[3], 2)));

        // 16 个 bit 的二进制字符串转成对应的 int 类型整数,低8位放在前面,高8位放到后面
        return Integer.parseInt(reverse ? str4 + str3 + str2 + str1 : str1 + str2 + str3 + str4, 2);
    }


    /**
     * 将指定数量 bit 的整数对应的二进制字符串转换成对应的 boolean 数组
     *
     * @param data data
     * @return boolean 数组
     */
    private boolean[] parseIntToBitArray(int data, int quantity) {
        if (data < 0) {
            throw new RuntimeException("data smaller than 0 is not supported");
        }

        char[] chars = Integer.toBinaryString(data).toCharArray();

        boolean[] result = new boolean[quantity];

        for (int i = 0; i < chars.length; i++) {
            result[quantity - chars.length + i] = Objects.equals("1", String.valueOf(chars[i]));
        }

        return result;
    }


    /**
     * 处理异常
     *
     * @param e 异常
     */
    protected void handleException(Throwable e) {
        log.error(errorMsg, e);
        throw new ModbusTCPException(errorMsg);
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDeviceName() {
        return deviceName;
    }
}
