# 第二讲GIT的基本操作流程和常用命令

首先创建一个文件，在该文件夹中使用如下命令

``` git
git init
```

该命令用来初始化git工厂的，使用了这个命令之后，当前的文件夹就是git工厂。如图所示创建好git的工厂之后，会有一个.git的隐藏文件夹，该文件夹是隐藏文件夹。

![git的基本操作流程和常用命令](https://ynkonghao.github.io/img/git/01/03.png)

我们在这个文件夹中添加一个a.txt的文件，当添加了a.txt之后，通过命令

```
git status
```

该命令可以查询文件的状态。如图所示，我们发现状态的类型是Untracked,这表示该文件还没有被GIT所管理（需要注意这个文件是红色显示的）。

![git的基本操作流程和常用命令](https://ynkonghao.github.io/img/git/01/04.png)

另外我们也可以在status之后加个`-s`的后缀

```
git status -s
```

该命令可以将状态简单的列出来，在文件前面有两个红色的?就表示该文件没有被git所管理。

![git的基本操作流程和常用命令](https://ynkonghao.github.io/img/git/01/05.png)

接着我们输入下一个命令

```
git add a.txt
```

该命令会把a.txt文件添加给git，添加完成之后通过`git status`看一下

![git的基本操作流程和常用命令](https://ynkonghao.github.io/img/git/01/06.png)

文件的颜色变成绿色了，而且状态也修改了，但是此时该文件还没有提交给git的工厂，我们需要通过命令

```
git commit -m "first"
```
这个命令可以把文件提交给git的工厂，-m表示我们要为这次提交设置一个消息，这样在将来要重置版本的时候有一个参考，-m是强制的，虽然有方法可以不用加这个消息，但是强烈不建议这样做。
提交之后我们通过`git status`再看一下情况。

![git的基本操作流程和常用命令](https://ynkonghao.github.io/img/git/01/07.png)

此时提示`nothing to commit, working tree clean`，这就表示文件提交成功了，那么这个提示为什么会显示working tree clean呢？这就需要简单了解git的原理。

对于git而言有两个区域需要我们了解，一个是`工作区(work area)`，另一个是`暂存区(stage area)`，工作区顾名思义就是git的文件夹，而暂存区比较特殊，我们添加或者修改一个文件之后，首先要把文件放到暂存区中，之后才提交给git的工厂，刚才新建一个文件查询状态的时候是红色的表示该次修改还没有在暂存区中，我们通过`git add a.txt`之后就把a.txt文件加入到了暂存区中，加入暂存区中之后，该文件的颜色会变成绿色。此时再通过`commit`提交给git的工厂。当提交完成之后，git会清空暂存区中的所有的内容。


接下来使用

```
git log
```

该命令可以查询git的日志，该命令我们会经常使用。

![git的基本操作流程和常用命令](https://ynkonghao.github.io/img/git/01/08.png)

我们看到了commit之后有一串哈希码，这个哈希码非常的重要，我们之后再来讨论，现在我们需要知道这个哈希码是这次提交的唯一标志，接着看到了我们的用户信息(在安装完成之后设置的)和该次提交的时间，最后是该次提交的消息。此时我们的第一个版本就已经被git所管理了。

接下来我们创建了一个新的文件b.txt，并且对a.txt进行了一次修改，使用命令`git status`看一下

![git的基本操作流程和常用命令](https://ynkonghao.github.io/img/git/01/09.png)

大家有没有注意到颜色是红色的，说明目前没有添加到git的`暂存区`，而且一个是modified状态的，另一个是untracked状态，这告诉我们a.txt是有过修改的文件，而b.txt则是新添加的文件，使用`git status -s`看一下

![git的基本操作流程和常用命令](https://ynkonghao.github.io/img/git/01/10.png)

a.txt前是M表示有过修改，而b.txt前是两个?表示没有被git所管理。使用命令`git add .`可以把工作区中的所有文件都提交。提交之后分别使用`git status`和`git status -s`之后看看情况

![git的基本操作流程和常用命令](https://ynkonghao.github.io/img/git/01/11.png)

我们发现颜色变成绿色了，这说明这两个文件已经在暂存区中了，通过-s我们可以看到相对简单的说明，a.txt前面是`M`说明该文件是被修改的，而b.txt前面是`A`说明该文件是新添加的。通过`git commit -m "add file"`再次提交给git的工厂，并且使用`git log`看一下

![git的基本操作流程和常用命令](https://ynkonghao.github.io/img/git/01/12.png)

我们发现已经有了两次cimmit，并且也列出了每次commit的唯一hash码和基本信息。以后只要我们进行一次commit都会被记录下来。

现在我们已经知道git如何提交代码给工厂了，接着我们尝试一下版本的还原，使用reset命令可以对版本进行还原

```
git reset --hard HEAD^^
```
--hard参数表示彻底回到某个版本，具体回到哪个版本通过`HEAD^^`，两个^表示回到上一个版本，三个^表示回到上上个版本，如果有很多个版本可以通过'HEAD~60'可以回到第60个版本。

![git的基本操作流程和常用命令](https://ynkonghao.github.io/img/git/01/13.png)
此时我们的文件夹中b.txt没有了，这说明已经回到了第一个版本，通过git log我们会发现当前只有最早的一个版本了。此时如果希望再恢复回来就得通过commit的hash标示才能回去了。使用命令

```
git reset --hard b7823a33c2534876a6afaf89923afcaf45cf1122
```

![git的基本操作流程和常用命令](https://ynkonghao.github.io/img/git/01/12.png)

现在git又回到了第二个版本的状态，并且b.txt文件已经被还原了。

以上就是git最基本的管理操作，大家有没有注意到，整个操作都是在本机操作的，并没有使用到任何的网络。下一部分将会带着大家学习更多基本操作

