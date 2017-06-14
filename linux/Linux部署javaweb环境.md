## Linux部署javaweb环境
1.jdk
2.Tomcat

### jdk安装

####1. 准备工作
- 查看系统版本版本
> uname -a
区分32位和64位系统
如果有x86_64就是64位的，没有就是32位的。后面是X686或X86_64则内核是64位的，i686或i386则内核是32位的
- 下载对应版本jdk
[http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

#### 2. 安装jdk
1. 建立java目录

> cd usr/local/ 
> makedir  java 

2. 上传JDK至java目录

3.  解压jdk、重命名
> tar -zxv -f  jdk-8u65-linux-i586.gz
> mv jdk1.8.0_65  javajdk
	
#### 3. 配置jdk环境变量
三种配置环境变量的方法
##### 第一种
1. vi /etc/profile
2. 将下面内容复制到最后一行

> JAVA_HOME=/usr/local/java/jdk1.6.0_01
> PATH=\$JAVA_HOME/bin:\$PATH
>CLASSPATH=.:\$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
> export JAVA_HOME
> export PATH
> export CLASSPATH

注意：
1. 将 /usr/share/jdk1.6.0_14改为你的jdk安装目录
2.  linux下用冒号“:”来分隔路径 
3.  \$PATH / \$CLASSPATH / $JAVA_HOME 是用来引用原来的环境变量的值在设置环境变量时特别要注意不能把原来的值给覆盖掉了，这是一种常见的错误。
4.  CLASSPATH中当前目录“.”不能丢,把当前目录丢掉也是常见的错误。
5.  export是把这三个变量导出为全局变量。
6.  大小写必须严格区分。

##### 第二种
这种方法更为安全，它可以把使用这些环境变量的权限控制到用户级别，如果你需要给某个用户权限使用这些环境变量，你只需要修改其个人用户主目录下的.bash_profile文件就可以了。 
·用文本编辑器打开用户目录下的.bash_profile文件 
·在.bash_profile文件末尾加入： 

export JAVA_HOME=/usr/share/jdk1.6.0_14 
export PATH=\$JAVA_HOME/bin:\$PATH 
export CLASSPATH=.:\$JAVA_HOME/lib/dt.jar:\$JAVA_HOME/lib/tools.jar 

##### 第三种
直接在shell下设置变量 
不赞成使用这种方法，因为换个shell，你的设置就无效了，因此这种方法仅仅是临时使用，以后要使用的时候又要重新设置，比较麻烦。 
只需在shell终端执行下列命令： 
export JAVA_HOME=/usr/share/jdk1.6.0_14 
export PATH=\$JAVA_HOME/bin:\$PATH 
export CLASSPATH=.:\$JAVA_HOME/lib/dt.jar:\$JAVA_HOME/lib/tools.jar 

#### 4. 测试jdk 

> java -version

```
java version "1.8.0_101"
Java(TM) SE Runtime Environment (build 1.8.0_101-b13)
Java HotSpot(TM) Client VM (build 25.101-b13, mixed mode)
```
安装JDK完毕.

### Tomecat

