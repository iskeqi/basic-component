@echo off
echo shutdown application...
curl -X POST http://localhost:8089/lsw/monitor/shutdown
echo shutdown application is success
pause