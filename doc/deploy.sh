#!/bin/bash

# 只要该脚本发生错误,就终止运行
set -e

# 本脚本中一共需要更改的3个变量
# jar 包所在路径
BASE_PATH=/data/wms
# jar 包名称,同时也是服务名称,此处不要加上 .jar 后缀
SERVER_NAME=littleswan-wms-1.0.0
# 健康检查 URL
HEALTH_CHECK_URL=http://127.0.0.1:8089/actuator/health/

# JVM 发生 heapError 时的日志存放路径
HEAP_ERROR_PATH=$BASE_PATH/heapError
# JVM 参数
JAVA_OPS="-Xms512m -Xmx512m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$HEAP_ERROR_PATH"

# 优雅关闭当前程序对应的进程
function stop() {
    echo "[stop] start stop $BASE_PATH/$SERVER_NAME"
    # 获取当前程序对应的进程ID
    PID=$(ps -ef | grep $BASE_PATH/$SERVER_NAME | grep -v "grep" | awk '{print $2}')
    # 如果 PID 长度大于零,表示当前程序已经处于运行状态,需要关闭当前程序对应的进程
    if [ -n "$PID" ]; then
        echo "[stop] $BASE_PATH/$SERVER_NAME is running, start kill -15 [$PID]"
        # 通过操作系统发送中断信号给程序,由程序自行关闭对应的进程
        kill -15 $PID
        # 每隔一秒查询当前程序对应的进程ID,以判断对应进程是否成功关闭。如果120秒后,还未关闭,则使用 kill -9 强制关闭程序
        for ((i = 0; i < 120; i++))
            do
                # 睡眠一秒
                sleep 1
                # 获取当前程序对应的进程ID
                PID=$(ps -ef | grep $BASE_PATH/$SERVER_NAME | grep -v "grep" | awk '{print $2}')
                if [ -n "$PID" ]; then
                    # 如果当前程序仍未关闭,则打印一个 . 且不换行
                    echo -e ".\c"
                else
                    # 如果成功关闭当前程序,则直接退出当前 for 循环
                    echo "[stop] stop $BASE_PATH/$SERVER_NAME success"
                    break
                fi
		    done

        # 120 秒后,对应进程仍未关闭,则使用 kill -9 强制关闭进程
        if [ -n "$PID" ]; then
            echo "[stop] $BASE_PATH/$SERVER_NAME failure, use kill -9 $PID"
            # 强制关闭对应进程
            kill -9 $PID
        fi
    # 如果 PID 长度等于零,表示当前程序不处于运行状态,不需要关闭当前程序对应的进程
    else
        echo "[stop] $BASE_PATH/$SERVER_NAME is not start, no need to stop"
    fi
}

# 使用 java -jar 命令启动当前程序
function start() {
    echo "[start] start run $BASE_PATH/$SERVER_NAME"
    echo "[start] JAVA_OPS: $JAVA_OPS"

    # 后台启动程序且不将控制台日志输出到文件中
    nohup java -server $JAVA_OPS -jar $BASE_PATH/$SERVER_NAME.jar > /dev/null 2>&1 &
    echo "[start] start $BASE_PATH/$SERVER_NAME success"
}

# 判断当前程序对应的进程是否正常运行
function status() {
    # 如果配置了健康检查,则进行健康检查
    if [ -n "$HEALTH_CHECK_URL" ]; then
        # 健康检查最大 120 秒,直到健康检查通过
        echo "[healthCheck] start use $HEALTH_CHECK_URL restful url to check program status";
        for ((i = 0; i < 120; i++))
            do
                # 请求健康检查地址,只获取状态码。
                result=`curl -I -m 10 -o /dev/null -s -w %{http_code} $HEALTH_CHECK_URL || echo "000"`
                # 如果状态码为 200,则说明健康检查通过
                if [ "$result" == "200" ]; then
                    # echo "[healthCheck] program is running";
                    break
                # 如果状态码非 200,则说明未通过。sleep 1 秒后,继续重试
                else
                    echo -e ".\c"
                    sleep 1
                fi
            done

        # 健康检查未通过,则异常退出 shell 脚本
        if [ ! "$result" == "200" ]; then
            echo "[healthCheck] program is not running, please check your program status in other ways";
            # tail -n 10 nohup.out
            exit 1;
        # 健康检查通过,打印最后 10 行日志,可能部署的人想看下日志。
        else
            # tail -n 10 nohup.out
            echo "[healthCheck] program is running";
        fi
    # 如果未配置健康检查,则直接进行提示
    else
        echo "[healthCheck] HEALTH_CHECK_URL is not config, please check your program status in other ways";
        # sleep 120
        # tail -n 50 nohup.out
    fi
}

# 升级应用程序
function upgrade() {
    echo "[upgrade] start exec mv $BASE_PATH/$1 $BASE_PATH/$SERVER_NAME.jar"
    sudo mv $BASE_PATH/$1 $BASE_PATH/$SERVER_NAME.jar
    echo "[upgrade] exec mv $BASE_PATH/$1 $BASE_PATH/$SERVER_NAME.jar done"
}

# 重启当前程序
function restart() {
    cd $BASE_PATH
    # 停止当前程序对应的进程
    stop
    # 启动当前程序
    start
    # 查询程序是否成功启动
    status
}

# 获取脚本参数,决定执行哪个函数。使用示例：sudo bash wms.sh start/stop/restart/status/upgrade
# 特殊说明,升级命令 sudo bash wms.sh upgrade 升级包名称
case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    status)
        status
        ;;
    restart)
        restart
        ;;
    upgrade)
        upgrade $2
        ;;
    *)
        restart
esac