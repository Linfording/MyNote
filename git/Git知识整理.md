# Git知识整理
## Git安装
Mac:https://sourceforge.net/projects/git-osx-installer/
Windows:https://git-for-windows.github.io/
Linux:apt-get install git

## WindowsGit
启动Git Bash
> git #查看git命令列表

### 1.Git基本命令
#### 创建Git仓库
1. 新建Git仓库目录
> mkdir test (创建文件夹test)
> cd test (切换到test目录)
> touch a.md (创建一个a.md文件)

2. 查看Git仓库状态
> git status
> 目前还不是一个git仓库，所以将会返回以下信息
> fatal: Not a git repository (or any of the parent directories): .git

2. 创建Git仓库
>  git init 
>  此时创建了一个git仓库了
> Initialized empty Git repository in F:/app/MyDaily/.git/
> Administrator@3NBXYFGA43UET0L MINGW64 /f/app/MyDaily (master)
> master 代表是当前库的master分支  为主分支

#### 提交代码
1. add命令
> git add a.md #将a.md添加到提交缓存里
> git  rm --cached a.md #将a.md 从提交缓存里移除

2. commit命令
> git commit -m 'first commit' #提交缓存，-m后面跟着的是提交信息

3. log命令
> git log #查看提交的信息

#### 分支
1.  branch命令
> git branch #查看该代码库下的分支
> git branch a #新建a分支

2. checkout命令
> git checkout a #切换到a分支下
> git checkout -b a #新建a分支，并且切换到a分支下

3. merge命令
> 1.切换到主分支
> 2.合并a分支
> git merge a

4. branch -d命令
> git branch -d a #合并完之后通过此命令删除a分支，若没合并完是不能删除的
> git branch -D a #可以强制删除a分支,不管是否合并完

#### 标签
> git tag v1.0 #代表在当前代码库下新建了一个v1.0的标签
> git tag #通过此命令查看tag记录
> git checkout v1.0 #切换到了v1.0tag的代码状态了

#### 更改用户名与邮箱
> 用户名和邮箱会在git log里查看到是谁提交的
> 在初次使用的时候是没有用户名和邮箱的，需要自己设置
> git config --global user.name "linfor" 
> git config --global user.email "linfonder@gmail.com" 

以上代码为全局配置，有些时候有些特定项目想要使用另外的邮箱或者用户名，先切换到你的项目目录下面，以上代码把--global参数去除，再重新执行一遍

#### alias命令自定义命令别名
有时候命令过于长或者复杂的时候可以采用alias自定义为自己比较容易记住的名字，方便使用
例：
> git config --global alias.psm 'push origin master ' 
> git config --global alias.plm 'pull origin master' 
之后用到'push orrigin master'和'pull origin master'可以直接用psm、plm代替了

**一个好用的log命令**
> git log --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset%s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit --date=relative

使用alias定义别名
> git config --global alias.lg "log --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset%s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit --date=relative"

下次再使用
> git lg


### 2. 向github提交代码
#### 生成SSH key
> ssh #查看是否有装ssh模块
> ssh -keygen -t rsa #指定rsa算法生成密钥，三个回车就可以了

#### 向github提交公钥
1.打开id_rsa.pub
2.将公钥内容提交到Github上
文件位置：linux/mac 系统在~/.ssh 下，win系统在/c/Documents and Settings/username/.ssh下

#### 测试是否成功
> ssh -T git@github.com
> \# 出现以下内容代表成功
Hi Linfording! You've successfully authenticated, but GitHub does not provide shell access.

#### 已有远程仓库拉取到本地
1.git进入想要建立git仓库的目录
> git clone git@[url] 
> git push origin master #提交master分支代码
> git pull origin master #拉取github上的代码

#### 已有本地项目合并到远程仓库
> git remote add [远程仓库名(自己取)]  [ssh远程地址]
> git remote add recyclerview git@github.com:Linfording/MyRecyclerView.git
> 第一次提交：
> git push -u recyclerview master

**注意：**
1. 远程地址，要是ssh地址
2. 远程仓库要是空的仓库
3. 第一次提交时：由于远程库是空的，我们第一次推送master分支时，加上了-u参数，Git不但会把本地的master分支内容推送的远程新的master分支，还会把本地的master分支和远程的master分支关联起来，在以后的推送或者拉取时就可以简化命令。
> git remote -v #查看该本地仓库关联了多少远程仓库
> git remote show recyclerview #查看远程仓库信息，可用于跟踪别人的push

#### 从远处仓库获取代码

1. fetch

> git fetch：相当于是从远程获取最新版本到本地，不会自动merge
> git fetch origin master
> git merge origin/master

fetch可以实现 从远程获取最新的版本到本地的test分支上之后再进行比较合并
>git fetch origin master:tmp
> git diff tmp 
> git merge tmp

2. pull
>  git pull：相当于是从远程获取最新版本并merge到本地
>  git pull origin master

#### git修改远程仓库地址 
方法有三种：

1. 修改命令
> git remote set-url origin [url]

2. 先删后加
> git remote rm origin
> git remote add origin [url]

3. 直接修改config文件

###  3.diff命令
### 4.checkout命令
### 5.stash命令
### 6.merge&rebase


