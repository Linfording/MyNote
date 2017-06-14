# CentOS6.5搭建SVN

系统版本：centos 6.5 mini
安装方式：YUM安装

## 安装
先确认本地是否已经安装subversion
> rpm -q subversion

如果有安装subversion，先删除旧版本
> yum remove subversion

安装svn
> yum -y install subversion

检查版本
> svnserve --version

## 创建代码库

系统采用为每个项目单独建一版本库的策略。配置文件，密码文件，访问控制文件等都放在版本库的conf 目录下。所以每次开始一个新项目都必须新建一个版本库，并重新配置各配置文件。还有很重要的一条，要求各组员重新配置客户端，包括服务器版本库路径，本地路径等信息。

创建SVN库：
> mkdir -p /opt/svn

创建repo代码库
> svnadmin create /opt/svn/repo

创建完成后，生成以下文件
> [root@localhost repo]# ll
> total 24
> drwxr-xr-x. 2 root root 4096 Jun 15 20:05 conf
> drwxr-sr-x. 6 root root 4096 Jun 15 20:26 db
> -r--r--r--. 1 root root    2 Jun 15 19:08 format
> drwxr-xr-x. 2 root root 4096 Jun 15 19:08 hooks
> drwxr-xr-x. 2 root root 4096 Jun 15 19:08 locks
> -rw-r--r--. 1 root root  229 Jun 15 19:08 README.txt

## 配置代码库
repo代码库的配置文件均在conf文件夹下
> cd /opt/svn/repo/conf

### 添加账户密码psswd配置
修改passwd文件，通过在文件中追加 账号 = 密码来创建用户
> vi passwd 

> [users]
> \# harry = harryssecret
> \# sally = sallyssecret
> zeng = 123456
> zhou = 123456

### 权限控制authz配置
svn的权限控制都是在authz文件中设定，该文件定义了哪些用户可以访问哪些目录
> vi authz
```
[groups]            #组
admin = hello,www   #创建一个admin组，将用户hello、www加入到组
[/]                 #根目录权限设置（就是“svn”这个文件夹）
aaa = rw            #aaa对svn下的所有版本库有读写权限
[repo:/]            #repo:/,表示对repo版本库下的所有资源设置权限
@admin = rw         #admin组的用户对repo版本库有读写权限
[repo2:/occi], ,表示对版本库repo2中的occi项目设置权限
[repo2:/occi/aaa], ,表示对版本库2中的occi项目的aaa目录设置权限　　权限主体可以是用户组、用户或*，用户组在前面加@，*表示全部用户。权限可以是w、r、wr和空，空表示没有任何权限。
```

### 配置版本库信息
> [root@admin conf]#vi svnserve.conf

追加以下内容：

> [general]
> \#匿名访问的权限，可以是read,write,none,默认为read
> anon-access = none
> \#使授权用户有写权限
> auth-access = write
> \#密码数据库的路径
> password-db = passwd
> \#访问控制文件
> authz-db = authz
> \#认证命名空间，subversion会在认证提示里显示，并且作为凭证缓存的关键字
> realm = repo(库的名字)

## 启动svn服务

### 防火墙配置
> vi /etc/sysconfig/iptables
> -A INPUT -m state --state NEW -m tcp -p tcp --dport 3690 -j ACCEPT #3690端口是svn默认端口
### SELinux配置
建议直接disable掉selinux
### 启动SVN
> svnserve -d -r /opt/svn/

其中-d 表示在后台运行， -r 指定服务器的根目录，这样
访问服务器时就可以直接用svn://服务器ip 来访问了。

> svnserve -d -r 文件夹路径--listen-host 192.168.100.131

如果服务器有多ip 的话–listen-host 来指定监听的ip 地址.我们可以在svn 客户端中通过svn://192.168.100.131 来访问svn 服务器

> svnserve -d –listen-port 9999 -r /opt/svn
 
通过svnserve 的–listen-port选项来指定一个端口, 不过这样的话客户端使用也必须家上端口, 如svn://192.168.100.200:8888/但是svn 默认端口是3690 ，之前步骤已经开启了，如果你要指定其他端口同样需要在防火墙上开放你指定的端口


停止svn服务
> killall svnserve
 
查看svn的服务进程
>netstat -tunlp | grep svn

检查是否启动成功
(查看是否存在svnserve 进程)
> ps -ef|grep svnserve

如果显示如下，即为启动成功：
> root     20748     1  0 Aug12 ?        00:00:00 svnserve -d -r /opt/svn
> root     26791 26777  0 06:07 pts/1    00:00:00 grep svnserve

后面可以看到自己的启动方式

## 客户端访问
下载tortoisesvn客户端

检出，地址：svn://服务器IP/repo
（由于 -r指向的服务器根目录，所以服务器IP/==》opt/svn/）

会弹出用户名密码登陆，输入即可

## 参考
http://zengestudy.blog.51cto.com/1702365/1791678
http://www.ha97.com/4467.html
http://www.centoscn.com/CentosServer/ftp/2015/0130/4600.html
http://www.centoscn.com/CentosServer/ftp/2014/1202/4218.html
http://weimouren.blog.51cto.com/7299347/1769402