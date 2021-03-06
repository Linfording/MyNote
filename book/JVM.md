﻿# 深入理解JVM虚拟机
## 第一部分走进Java
### 第一章
#### 1.2Java技术体系
Sun官方定义的java技术体系包括以下几个部分:
- java程序设计语言
- 各种硬件平台上的JVM
- Class文件格式
- JavaAPI类库
- 来自商业机构和开源社区的第三方Java类库

JDK:
- Java程序设计语言
- Java虚拟机
- JavaApi

JDK是支持Java程序开发的最小环境

JRE:
- JavaSE API子集
- Java虚拟机
JRE是支持Java程序运行的标准环境

按技术服务的领域来划分可以分为4个平台:
- Java Card
- Java ME
- Java SE
- Java EE

#### 1.3 Java发展史
Oak
> java前身

JDK1.0
> - 技术代表：纯解释执行的Java虚拟机(Sun Classic VM)；Applet； AWT

JDK1.1
> - java技术的一些基础支撑点都是在该版本中发布的
> - 技术代表： JAR文件格式；JDBC；JavaBeans；RMI
>-  语言发展：内部类(Inner Class)；反射(Reflection)

JDK1.2
> - 里程碑：把java技术体系拆分为3个方向:J2SE,J2EE,J2ME
> - 技术代表：EJB，Java Plun-in,Java IDL,Swing等
>- 并存过3个虚拟机：Classic VM、HotSpot VM和Exact VM

JDK1.3
>-  HotStop作为SunJDK的默认虚拟机
>- Java2D API，新增JavaSound类库

JDK1.4
> - 技术新特性：正则表达式，异常链，NIO，日志类，XML解析器和XSLT转换器
> - ClassicVM退出历史舞台

JDK1.5
> 语法特性:
> - 自动装箱，泛型，动态注解，枚举，可变长参数，循环遍历(foreach循环)

JDK1.6
> - 启用JavaSE6,JavaEE6,JavaME6等命名方式
> - 提供动态语言支持,提供编译API和微信HTTP服务器API
> - JVM虚拟机内部重大改进,同步锁，垃圾收集,类加载等方面的算法都有改进

JDK1.7
> -提供新的G1收集器，加强对非Java语言的调用支持，升级类加载架构

JDK1.8
> Lambda表达

#### 1.4 Java虚拟机发展史
1.4.1 Sun Classic VM、Exact VM
- 早期虚拟机

1.4.2 Sun HotSpot VM
- Sun 高性能虚拟机

1.4.3 Sun Mobile-EmbeddeVM、Meta-Circular VM
- 研究用虚拟机  

1.4.4 BEA Jrockit、IBM J9 VM
- BEA，IBM自己研发的高性能虚拟机

1.4.5 Azul VM 、BEA Liquid VM
- 特定硬件平台专有的虚拟机,平台下超高性能

1.4.6 Apache Harmony、Google Android Dalvik VM

1.4.7 Mircrosoft JVM及其他

#### 1.5未来展望
1.模块化
2.混合语言
3.多核并行
4.进一步丰富语法
5.64位虚拟机
#### 1.6自己编译JDK
开源JDK：Apache Harmonym，OpenJDK
##### 1.6.1	获取JDK源码
OpenJDK：Sun在2006年末把Java开源而形成的项目。
Oracle JDK：存在一些Open JDK中没有的，商用闭源的功能。

http://jdk.java.net/

# 第二部 分自动内存管理机制
## 第二章 Java内存区域与内存溢出异常
java与c++之间有一堵由内存动态分配和垃圾收集技术所围成的“高墙”，墙外的人想进去，墙内的人却想出来
### 2.2 运行时数据区域
Java虚拟机在执行Java程序的过程中会把它所管理的内存划分为若干个不同的数据区域。这些区域都有各自的用途，以及创建和销毁的时间，有的随着虚拟机进程的启动而存在，有些区域则依赖用户线程的启动和结束而建立和销毁。

运行时数据区域：
由所有线程共享的数据区：
- 方法区
- 堆

线程隔离的数据区
- 虚拟机栈
- 本地方法栈
- 程序计数器

#### 2.2.1 程序计数器(Program Counter Register)
当前线程所执行的字节码的行号指示器。

 **程序计数器：** 是一块较小的内存空间，它可以看作是当前线程所执行的字节码的行号指示器。在虚拟机的概念模型里(仅是概念模型，各种虚拟机可能会通过一些更高效的方式去实现)，字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令，分支、循环、跳转、异常处理、线程恢复等基础功能都需要依赖这个计数器来完成。
**每条线程私有一个程序计数器**
为了在多线程，线程切换运行后恢复到正确的执行位置。
**线程执行的Java方法时**，计数器记录的是正在执行的虚拟机字节码指令的地址；
**线程执行的Native方法时**，计数器值则为空。
此内存区是唯一一个在Java虚拟机规范中没有规定任何OutOfMemoryError情况的区域
#### 2.2.2 Java虚拟机栈(Java Virtual Machine Stacks)
**描述Java方法执行的内存模型：**栈帧

**栈帧：**局部变量表，操作数栈，动态链接，方法出口

**与程序计数器一样，Java虚拟机栈也是线程私有**他的生命周期与线程相同。虚拟机栈描述的是Java方法执行的内存模型：每个方法在执行的同时都会创建一个栈帧(Stack Frame)用于存储局部变量表、操作数栈、动态连接、方法出口等信息。每一个方法从调用直至执行完成的过程，就对应着一个栈帧在虚拟机栈中入栈到出栈的过程。

经常提及的堆内存(Heap)和栈内存(Stack)是比较粗糙的分发。其中的栈所指的其实是现在讲的虚拟机中局部变量表部分

**局部变量表：**
存放了编译期可知的各种基本数据类型(primitive)、引用对象(reference类型，它不等同于对象本身，可能是一个指向对象起始地址的引用指针，也可能是指向一个代表对象的句柄或其他与此对象相关的位置)和 returnAddress类型(指向了一条字节码指令的地址)

**类型占用的空间：**
其中64位长度的long和double类型的数据会占用2个局部变量的空间(slot)，其余的数据类型只占用1个。

**局部变量表所需要的内存空间：**
在编译期间完成分配，当进入一个方法时，这个方法需要在帧中分配多大的局部变量空间是完全确定的，在方法运行期间不会改变局部变量表的大小。

**两种异常情况：**
**StackOverflowError异常：**
如果线程请求的栈深度大于虚拟机所允许的深度，将抛出StackOverflowError异常；
无论是栈深度无限增加，还是栈帧占的空间太大都出现的是StackOverflowError

**OutOfMemoryError异常：**
在申请新内存时，虚拟机分配给线程的内存大小中无法再分配新的内存，就会出现OutOfMemoryError异常。

**栈深度：**
每次方法调用都会有一个栈帧压入虚拟机栈，操作系统给JVM分配的内存是有限的，JVM分配给“虚拟机栈”的内存是有限的。
如果方法调用过多，导致虚拟机栈满了就会溢出。
这里栈深度就是指栈帧的数量。
比如无限的递归调用方法把栈帧压入虚拟机，这时就会抛出StackOverflowError

#### 2.2.3 本地方法栈(Native Method Stack)
本地方法栈与虚拟机栈所发挥的作用是非常相似的，他们之间的区别不过是虚拟机栈为虚拟机执行Java方法(也就是字节码)服务，而本地方法栈则为虚拟机使用到的Native方法服务。在虚拟机规范中对本地方法栈中使用的语言，使用方式与数据结构并没有强制规定，因此具体的虚拟机可以自由实现它。甚至有的虚拟机(比如 Sun HotSpot虚拟机)直接把本地方法栈和虚拟机栈合二为一。与虚拟机一样，本地方法栈区域也会抛出StackOverflowError和OutOfMemoryError异常

#### 2.2.4 Java堆(Java Heap)
实例对象和数组在堆上分配内存

对于大多数应用来说，Java堆(Java Heap) 是Java虚拟机所管理的内存中最大的一块。是存放对象实例，几乎所有的对象实例都在这里分配内存。这一点在Java虚拟机规范中的描述是：所有的对象实例以及数组都要在堆上分配，但是随着JIT编译器的发展与逃逸分析技术逐渐成熟，栈上分配、标量替换优化技术将会导致一些微妙的变化发生，所有对象都在堆上也渐渐变得不那么“绝对”了

Java堆是垃圾收集器管理的主要区域，因此很多时候也被称作“GC堆”。从内存回收的角度来看，由于现在收集器基本都采用分代收集算法，所以Java堆中还可以细分为：新生代和老年代；再细致一点的有Eden空间、From Survivor空间、To Survivor空间等。从内存分配的角度来看，线程共享的Java堆中可能划分出多个线程私有的分配缓冲区(Thread Local Allocation Buffer，TLAB)。不过无论如何划分，都与存放内容无关，无论哪个区域，存储于的都仍是对象实例，进一步划分的目的是为了更好地回收内存，或者更快的分配内存。这里暂不讨论。
根据Java虚拟机规范的规定，Java堆可以处于物理上不连续的内存空间中，只要逻辑上是连续的即可，就像我们的磁盘空间一样。在实现时，既可以实现成固定大小，也可以是可扩展的，不过当前主流的虚拟机都是按照可扩展来实现的(通过-Xmx和-Xms控制)。如果在堆中没有内存完成实例分配，并且堆也无法再扩展时，将会抛出OutOfMemoryError异常。

#### 2.2.5 方法区(Method Area)
已被加载的类信息，常量，静态变量，编译后的代码

与Java堆一样，是各个线程共享的内存区域，它用于存储已被虚拟机加载的类信息，常量，静态变量、即时编译器编译后的代码等数据。虽然Java虚拟机规范把方法区描述为堆的一个逻辑部分，但是它却有一个别名叫做Non-Heap(非堆)，目的为了与Java堆区分开来。

HotSpot用永久带的方法来实现方法区，但是这样更容易出现内存溢出的问题，所以现在在逐渐的放弃永久代，逐步改为采用Native Memory来实现方法区的规划。

这个区域内存回收目标主要针对常量池的回收和对类型的卸载。

#### 2.2.6 运行时常量池(Runtime Constant Pool)
运行时常量池是方法区的一部分。Class文件中除了有类的版本、字段、方法、接口等描述信息外，还有一项信息是常量池，用于存放编译期生成的各种字面量和符号引用，这部分内容将在类加载后进入方法区的运行时常量池中存放。

运行时常量池相对于Class文件常量池的另外一个重要特征是具备动态性，Java语言不要求常量一定只有编译期才能产生，也就是并非预置入Class文件中常量池的内容才能进入方法去运行时常量池，运行期间也可能将新的常量放入池中，这种特性被开发人员利用得比较多的是String类的intern()方法

#### 2.2.7 直接内存(Direct Memory)
直接内存并不是虚拟机运行时数据区的一部分，也不是Java虚拟机规范中定义的内存区域。但是这部分内存也被频繁的使用，而且也可能导致OutOfMemoryError异常出现。

在JDK1.4中心加入了NIO(New Input/Output)类，引入了一种基于通道(Channel)与缓冲区(Buffer)的I/O方式，他可以使用Native函数库直接分配堆外内存，然后通过一个存储在Java堆中的DirectByteBuffer对象作为这快内存的引用进行操作。这样能在一些场景中显著提高新能，因为避免了Java堆和Native堆中来回复制数据。

显然本机直接内存的分配不会受到Java堆大小的限制，但是，既然是内存，肯定还是会受到本机总内存(包括RAM以及SWAP区或者分页文件)大小以及处理器寻址空间的限制。服务器管理员在配置虚拟机参数时，会根据实际内存设置-Xmx等参数信息，但是经常忽略直接内存，使得各个内存区域总和大于物理内存限制(包括物理的和操作系统级的限制)，从而导致动态扩展时出现OutOfMemoryError异常

### 2.3 HotSpot虚拟机对象探秘
内存中数据的其他细节，比如他们是如何创建，如何布局，以及如何访问的。对于这样涉及细节的问题，必须把讨论范围限定在具体的虚拟机和集中在某一个内存区域上才有意义。基于实用优先的原则，作者以HotSpot虚拟机和Java堆为例，深入探讨HotSpot在Java堆中对对象分配，布局和访问的全过程。

#### 2.3.1 对象的创建


## 第三章 垃圾收集器与内存分配策略

## 第四章 虚拟机性能监控与故障处理工具

## 第五章 调优案例分析与实战

# 第三部分 虚拟机执行子系统

## 第六章 类文件结构

## 第七章 虚拟机类加载机制

## 第八章 虚拟机字节码执行引擎

## 第九章 类加载及执行子系统的案例与实战

# 第四部分 程序编译与代码优化

## 第十章 早期(编译器) 优化

## 第十一章 晚期(运行期) 优化

# 第五部分 高效并发

## 第十二章 Java内存模型与县城

## 第十三章 线程安全与锁优化
