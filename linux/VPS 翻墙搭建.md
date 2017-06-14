# VPS 翻墙搭建
1. VPN搭建
2. SS搭建

## Openvzvps 搭建VPN 
系统：Centos-6-x86 minimal
步骤： 
1. 安装pptp软件
	- pptpd命令
2. 安装Vpn
3. 添加用户
4. 监控VPN
### 1.安装pptp
> yum search pptp-setup

pptpd命令
> 重启PPTPD：/etc/init.d/pptpd restart
> 停止PPTPD：/etc/init.d/pptpd stop
### 2.安装Vpn
> wget http://www.hi-vps.com/shell/vpn_centos6.sh
> chmod a+x vpn_centos6.sh
> bash vpn_centos6.sh

### 3.添加用户
> vi /etc/ppp/chap-secrets

按照以下格式配置用户名和密码
> usernmae pptpd password *

### 4.监控VPN
查看VPN在线用户
> ifconfig | grep ppp
> last | grep still | grep ppp

查看VPN用户实时流量信息
> apt-get install ifstat
> ifstat

## Openvzvps 搭建 SS
1. vps上一键安装Shadowsocks脚本
2. 启动Shadowsocks，设置开机自启
3. 客户端打开Shadowsocks配置
### 1.一键安装SS
> wget –no-check-certificate https://raw.githubusercontent.com/teddysun/shadowsocks_install/master/shadowsocks.sh
> chmod +x shadowsocks.sh
> ./shadowsocks.sh 2>&1 | tee shadowsocks.log

遇到Y按Y
提示输入密码，为SS登陆密码
提示输入端口，为SS登陆端口
### 2.启动shoadwsocks
> - 启动：/etc/init.d/shadowsocks start
> - 停止：/etc/init.d/shadowsocks stop
> - 重启：/etc/init.d/shadowsocks restart
> - 状态：/etc/init.d/shadowsocks status
> - 卸载：./shadowsocks.sh uninstall
> - 配置文件路径：/etc/shadowsocks.json
### 3.客户端配置
1. 打开Shadowsocks
	- 服务器IP：VPS公网IP
	- 服务器端口：ss安装时的端口
2. 启用系统代理
3. 系统代理模式：
	 - PAC：国内网不采用代理，国外才采用
	 - 全局：全部采用代理
4. 允许来自局域网的连接：
- 允许局域网下的网络设备可以通过用次IP PORT进行代理访问网络