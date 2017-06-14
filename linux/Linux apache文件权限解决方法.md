# Linux apache文件权限解决方法
**问题**：由于项目是通过ssh上传至服务器，而运行项目使用的是apache的用户，两个用户不一致，导致每次未获得写入权限问题。
**解决思路**：
**方法1**：将apache用户允许ssh登陆，传入项目通apache项目，这样上传的用户则是apache项目，文件所有者一般默认获得所有权限，则无需再赋予权限
**方法2**：新建用户，允许ssh登陆，apache通过该新建用户启动，则上传用户，和apache用户是同一用户，这样文件的所有者和操作者是同一用户，也无需再赋予权限。


## Linux 新建用户
> useradd username
> passwd password

建立普通用户后，普通用户不一定能通过ssh连接到服务器
> **如何让ssh只允许指定的用户登录**
**方法1**：在/etc/pam.d/sshd文件第一行加入
auth required pam_listfile.so item=user sense=allow file=/etc/sshusers
onerr=fail
然后在/etc下建立sshusers文件,编辑这个文件,加入你允许使用ssh服务的用户名,不用重新启动sshd服务。
添加2个用户
zhangsan
lisisi         
**方法2**：pam规则也可以写成deny的
auth required pam_listfile.so item=user sense=deny file=/etc/sshusers
onerr=succeed
**方法3**：pam规则可以使用group限制
auth required pam_listfile.so item=group sense=allow file=/etc/security/allow_groups onerr=fail
在allow_groups文件按中加入组名，主要一定要加root
**方法4**：在sshd_config中设置AllowUsers，格式如
AllowUsers a b c
重启sshd服务，则只有a/b/c3个用户可以登陆

## Linux查看用户列表

> cat /etc/shadow
> cat /etc/passwd #系统存在的所有用户名
> cat /etc/group #文件包含所有组


在/etc/group 中的每条记录分四个字段：

第一字段：用户组名称；

第二字段：用户组密码；

第三字段：GID

第四字段：用户列表，每个用户之间用,号分割；本字段可以为空；如果字段为空表示用户组为GID的用户名；

## linux 下修改 apache 启动的所属用户和组
**进入httpd.conf配置文件**
>vi /etc/httpd/conf/httpd.conf

**更改**
>User apache
>Group apache

**重启 apache**
>service httpd restart


## 递归改变子目录属性
> chmod 777 html  #改变html目录的权限为
> chmod -R 777 html            #改变html目录及子目录

> chown flatnose html         改变html目录所属用户
> chown -R flatnose html        改变html目录及子目录

> chown :ftp gstore    改变gstore目录
> chown -R :ftp  gstore  改变gstore目录及子目录