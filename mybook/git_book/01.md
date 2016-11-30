# 第一讲git的基础和安装
![](https://ynkonghao.github.io/img/git/01/00.png)
如上图所示，使用svn是基于中央工厂的管理方式，首先由项目的管理人员把项目的架构搭建好，之后传到svn的服务器上，之后每个项目组的成员通过checkout获取这个项目的架构，当某个成员修改了代码之后通过commit提交给中央工厂，其他成员需要通过update来更新工厂中的内容，这种方式最大的缺点是当没有网络之后这个工作无法执行，如果中央工厂的服务器断了也没有办法继续工作。

GIT解决了SVN中的问题，它的没有版本都是在本地运行的，如果没有网络依然可以进行开发，因为本地保存了所有的版本信息。


## git的下载

可以通过访问[http://www.git-scm.com](http://www.git-scm.com)访问git的网站,
点击download下载git，目前是2.10.2版本
![](https://ynkonghao.github.io/img/git/01/01.png)
下载完成git之后，进入命令提示符输入命令`git --version`来检测git是否安装成功
如图所示就说明git安装成功了。
![](https://ynkonghao.github.io/img/git/01/02.png)

之后我们要设置git的两个基本信息，这个信息会在未来的提交代码的时候有用，能够让其他人员可以明白究竟是谁提交的代码。

```
#设置用户名和用户邮箱
git config --global user.name "ynkonghao"
git config --global user.email "ynkonghao@gmail.com"
```

设置完成之后可以去我的文档目录下有个`.gitconfig`的文件，这个文件里面就存储了git的基本配置信息。到这里git就算安装完成了!

