#CentOS 6.5系统安装配置LAMP(Apache+PHP5+MySQL)服务器环境
- 准备
1. 配置防火墙，开启80端口、3306端口
2. 关闭SELINUX
- 安装
1. 添加第三方源
1. 安装Apache
	- 安装Apache
	- 启动Apache
2. 安装MySQL
	- 安装MySQL
	- 启动MySQL
	- 设置MySQL
3. 安装PHP5
	- 安装PHP
	- 安装PHP组件，使PHP支持MySQL
	- 启动
4. 配置
4. 测试
5. 参考

## 准备
系统版本：centos 6.5 mini

### 1.配置防火墙
> vi /etc/sysconfig/iptables
> -A INPUT -m state --state NEW -m tcp -p tcp --dport 80 -j ACCEPT #允许80端口通过防火墙
> -A INPUT -m state --state NEW -m tcp -p tcp --dport 3306 -j ACCEPT #允许3306端口通过防火墙

备注：很多网友把这两条规则添加到防火墙配置的最后一行，导致防火墙启动失败，


正确的应该是添加到默认的22端口这条规则的下面


如下所示：
```
######################################################################
    # Firewall configuration written by system-config-firewall
    # Manual customization of this file is not recommended.
    *filter
    :INPUT ACCEPT [0:0]
    :FORWARD ACCEPT [0:0]
    :OUTPUT ACCEPT [0:0]
    -A INPUT -m state --state ESTABLISHED,RELATED -j ACCEPT
    -A INPUT -p icmp -j ACCEPT
    -A INPUT -i lo -j ACCEPT
    -A INPUT -m state --state NEW -m tcp -p tcp --dport 22 -j ACCEPT
    -A INPUT -m state --state NEW -m tcp -p tcp --dport 80 -j ACCEPT
    -A INPUT -m state --state NEW -m tcp -p tcp --dport 3306 -j ACCEPT
    -A INPUT -j REJECT --reject-with icmp-host-prohibited
    -A FORWARD -j REJECT --reject-with icmp-host-prohibited
    COMMIT
######################################################################
```
### 2.关闭SeLinux
 -  SELinux(Security-Enhanced Linux) 是美国国家安全局（NSA）对于强制访问控制的实现，是 Linux历史上最杰出的新安全子系统。在这种访问控制体系的限制下，进程只能访问那些在他的任务中所需要文件。SELinux 默认安装在 Fedora 和 Red Hat Enterprise Linux 上。
 - 虽然SELinux很好用，但是在多数情况我们还是将其关闭，因为在不了解其机制的情况下使用SELinux会导致软件安装或者应用部署失败
 - 以下就是关闭SELinux的方法

查看selinux状态
> 查看selinux的详细状态，如果为enable则表示为开启
>  # /usr/sbin/sestatus -v

查看selinux的模式
>  # getenforce

关闭selinux
(由于本人系统查看SeLinux状态是disable，所以并未用上关闭方法，未知可行)
- 方法1：
> 永久性关闭（这样需要重启服务器后生效）
>  # sed -i 's/SELINUX=enforcing/SELINUX=disabled/' /etc/selinux/config
>  
>临时性关闭（立即生效，但是重启服务器后失效）
>  # setenforce 0 #设置selinux为permissive模式（即关闭）
>  # setenforce 1 #设置selinux为enforcing模式（即开启）

- 方法2：
> 关闭SELINUX
> vi /etc/selinux/config
>  #SELINUX=enforcing #注释掉
>  #SELINUXTYPE=targeted #注释掉
> SELINUX=disabled #增加
> :wq!#保存退出
> shutdown -r now#重启系统

## 安装
### 1.添加第三方源
CentOS默认yum源软件版本太低了，要安装最新版本的LAMP，这里使用第三方yum源
> wget http://www.atomicorp.com/installers/atomic
> 若没有wget命令首先使用默认源安装wget
> yum install wget
> sh ./atomic  #安装

>yum clean all #清除当前yum缓存
>yum makecache #缓存yum源中的软件包信息
>yum repolist #列出yum源中可用的软件包

### 2.安装Apache
#### 2.1 安装

> yum install httpd #根据提示，输入Y安装即可成功安装
> /etc/init.d/httpd start #启动Apache

**备注：Apache启动之后会提示错误：**
> httpd:httpd: Could not reliably determine the server's fully qualif domain name, using ::1 for ServerName

**解决办法：**
> vi /etc/httpd/conf/httpd.conf #编辑
> ServerName www.example.com:80 #去掉前面的注释

#### 2.2 启动Apache
> chkconfig httpd on #设为开机启动
> /etc/init.d/httpd restart #重启Apache

### 3.安装MySQL

#### 3.1 安装
> yum install mysql mysql-server #询问是否要安装，输入Y即可自动安装,直到安装完成

#### 3.2 启动
> /etc/init.d/mysqld start #启动MySQL
> chkconfig mysqld on #设为开机启动
> cp /usr/share/mysql/my-medium.cnf /etc/my.cnf #拷贝配置文件（注意：如果/etc目录下面默认有一个my.cnf，直接覆盖即可）

#### 3.3 设置
1. 为root账户设置密码
> mysql_secure_installation # 回车两次接着设置New password，接着有四个确认，直接回车即可。(mysql1234)
> 最后出现：Thanks for using MySQL!

4. 更改mysql编码
> 更改mysql配置，vi etc/my.cnf文件设置默认编码为utf8，在最后添加如下代码：
> [mysql]
> default-character-set=utf8

5. 为外网设置可访问数据库用户
> 新增用户授权任意主机登陆mysql
> grant <权限> on <数据库名>.<访问主机IP> to < username> identified by < password>
> grant select,insert,update,delete on \*.* to testuser identified by '1234';
>  # *代表任意的

>设置用户操作权限
>grant all privileges on \*.* to testuser identified by "123456" WITH GRANT OPTION ;　　//设置用户testuser，拥有所有的操作权限，也就是管理员 ；

### 4.安装PHP
#### 4.1 安装PHP
> yum install php #根据提示输入Y直到安装完成

#### 4.2 安装PHP组件，使PHP支持MySQL
>yum install php-mysql php-gd libjpeg* php-imap php-ldap php-odbc php-pear php-xml php-xmlrpc php-mbstring php-mcrypt php-bcmath php-mhash libmcrypt
> 这里选择以上安装包进行安装
> 根据提示输入Y回车

#### 4.3 启动
> /etc/init.d/mysqld restart #重启MySql
> /etc/init.d/httpd restart #重启Apche

## 配置
(本机并未配置，以下为网络资料，效果以实际使用效果为准)
**1. Apache配置**
vi /etc/httpd/conf/httpd.conf #编辑文件
ServerTokens OS　 在44行 修改为：ServerTokens Prod （在出现错误页的时候不显示服务器操作系统的名称）
ServerSignature On　 在536行 修改为：ServerSignature Off （在错误页中不显示Apache的版本）
Options Indexes FollowSymLinks　 在331行 修改为：Options Includes ExecCGI FollowSymLinks（允许服务器执行CGI及SSI，禁止列出目录）
 #AddHandler cgi-script .cgi　在796行 修改为：AddHandler cgi-script .cgi .pl （允许扩展名为.pl的CGI脚本运行）
AllowOverride None　 在338行 修改为：AllowOverride All （允许.htaccess）
AddDefaultCharset UTF-8　在759行 修改为：AddDefaultCharset GB2312　（添加GB2312为默认编码）
Options Indexes MultiViews FollowSymLinks  在554行 修改为 Options MultiViews FollowSymLinks（不在浏览器上显示树状目录结构）
DirectoryIndex index.html index.html.var  在402行 修改为：DirectoryIndex index.html index.htm Default.html Default.htm
index.php Default.php index.html.var （设置默认首页文件，增加index.php）
KeepAlive Off  在76行 修改为：KeepAlive On （允许程序性联机）
MaxKeepAliveRequests 100  在83行 修改为：MaxKeepAliveRequests 1000 （增加同时连接数）
:wq #保存退出
/etc/init.d/httpd restart #重启
rm -f /etc/httpd/conf.d/welcome.conf /var/www/error/noindex.html #删除默认测试页
**2. php配置**
vi /etc/php.ini #编辑
date.timezone = PRC #在946行 把前面的分号去掉，改为date.timezone = PRC
disable_functions = passthru,exec,system,chroot,scandir,chgrp,chown,shell_exec,proc_open,proc_get_status,ini_alter,ini_alter,ini_restore,dl,openlog,syslog,readlink,symlink,popepassthru,stream_socket_server,escapeshellcmd,dll,popen,disk_free_space,checkdnsrr,checkdnsrr,getservbyname,getservbyport,disk_total_space,posix_ctermid,posix_get_last_error,posix_getcwd, posix_getegid,posix_geteuid,posix_getgid, posix_getgrgid,posix_getgrnam,posix_getgroups,posix_getlogin,posix_getpgid,posix_getpgrp,posix_getpid, posix_getppid,posix_getpwnam,posix_getpwuid, posix_getrlimit, posix_getsid,posix_getuid,posix_isatty, posix_kill,posix_mkfifo,posix_setegid,posix_seteuid,posix_setgid, posix_setpgid,posix_setsid,posix_setuid,posix_strerror,posix_times,posix_ttyname,posix_uname
 #在386行 列出PHP可以禁用的函数，如果某些程序需要用到这个函数，可以删除，取消禁用。
expose_php = Off  #在432行 禁止显示php版本的信息
magic_quotes_gpc = On  #在745行 打开magic_quotes_gpc来防止SQL注入
short_open_tag = ON  #在229行支持php短标签
open_basedir = .:/tmp/  #在380行 设置表示允许访问当前目录(即PHP脚本文件所在之目录)和/tmp/目录,可以防止php木马跨站,如果改了之后安装程序有问题(例如：织梦内容管理系统)，可以注销此行，或者直接写上程序的目录/data/www.111cn.net/:/tmp/
:wq #保存退出
/etc/init.d/mysqld restart #重启MySql
/etc/init.d/httpd restart #重启Apche

## 测试

Apache目录
> apache默认的程序目录是/var/www/html

测试
> cd /var/www/html
放入测试文件
> vi index.php #输入下面内容
<?php
phpinfo();
?>
:wq #保存退出
在客户端浏览器输入http://服务器IP地址/index.php，可以看到结果

## 参考
http://www.111cn.net/sys/CentOS/68941.htm
http://blog.chinaunix.net/uid-20127169-id-4831773.html
http://blog.sina.com.cn/s/blog_a22f65fd0102vqa6.html