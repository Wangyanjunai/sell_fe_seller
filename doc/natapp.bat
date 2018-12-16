@echo off
echo 正在启动NATAPP内网穿透服务过程，请稍等……
ipconfig
echo 查看本机局域网ip地址完成！按任意键继续……感谢您的使用！
echo. & pause
cd /d C:\Users\wangyanjun\
natapp.exe -authtoken=99f7af6e31464c91
echo 启动NATAPP内网穿透服务服务完成！按任意键继续……感谢您的使用！
echo. & pause