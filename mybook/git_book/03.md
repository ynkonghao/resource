# 第三讲GIT的四种文件状态和索引
通过上一节的内容，大家已经知道了git的几个常用命令，这一讲将会给大家介绍GIT的几种文件状态，文件状态和git的索引息息相关，所以该部分的内容可以帮助大家很好的理解git的原理。git的文件状态如下图所示

![git的文件状态和索引](https://ynkonghao.github.io/img/git/02/01.png)

git的文件有如下四种状态：
- Untracked
- Unmodified
- Modified
- Staged

在开始讲解这四种状态之前，我们需要了解GIT中比较重要的一个知识，索引档案(index)。我们首先创建一个文件夹，并且通过`git init`将其设置为git的工厂，之后我们查看一下.git文件夹。

![git的文件状态和索引](https://ynkonghao.github.io/img/git/02/02.png)

这个文件夹中有三个文件:HEAD,config,description。接下来我们回到上一级目录，创建一个文件a.txt,并且使用`git add .`将其添加到暂存区中。然后再到.git文件夹中查询一下文件

![git的文件状态和索引](https://ynkonghao.github.io/img/git/02/03.png)

有没有发现多了一个index文件，这个文件就是git的索引文件，当我们在工作区中进行了任意的操作之后git都会向这个文件中输入操作信息，当我们利用`git status`查询的时候，就会用index来和版本库中的最新版本进行比较，以此确定哪些文件是什么状态。

在了解了index的索引档之后，我们就开始研究几种状态了，首先，创建一个b.txt的文件，使用`git status`查询。

![git的文件状态和索引](https://ynkonghao.github.io/img/git/02/04.png)

此时索引区中已经有了一个新的文件b.txt，但是git却发现这个文件还没有被管理(git究竟是怎么没有发现该文件没有管理的呢？这个后面会详细介绍)，就将其设置为Untracked状态，之后执行add

![git的文件状态和索引](https://ynkonghao.github.io/img/git/02/05.png)

此时a.txt和b.txt都已经被git所识别，并且将这个操作添加到了index中，index又和当前的最高版本进行比较，发现这两个文件都没有在仓库中，所以就提示new file,目前的状态已经变成了 `Staged`状态。之后执行commit。

![git的文件状态和索引](https://ynkonghao.github.io/img/git/02/06.png)

当提交只会，版本库中的内容和index的内容完全一致，所以就提示没有任何修改发现，这也就是`Unmodified`状态。之后我们向b.txt中添加一些内容

![git的文件状态和索引](https://ynkonghao.github.io/img/git/02/07.png)

此时这个添加操作会被记录在index中，而index和最新的版本不同，所以从`Unmodified`状态变更到了`Modified`状态。所以`Modified`状态就是已经被GIT管理但是进行过修改，此时通过add之后又会进入到`Staged`状态，通过commit之后再次到`Unmodified`状态。

![git的文件状态和索引](https://ynkonghao.github.io/img/git/02/08.png)

最后就是`Unmodified`怎么到`Untracked`状态呢？这种情况就是当GIT版本库中的文件直接被删除，但是当前文件夹没有被删除时会出现。使用如下命令

```
git rm --cached b.txt
```
该命令执行完git版本库中的内容会被删除，但当前文件夹中的b.txt会存在，可以通过git ls-files查询版本库中的内容

![git的文件状态和索引](https://ynkonghao.github.io/img/git/02/09.png)

此时版本库中没有了b.txt而文件夹中却有，index首先发现文件夹中有个b.txt而自己不知道，所以将b.txt设置为`Untracked`状态，但是它还发现原来的索引库中还有b.txt的内容（注意这里是文件内容，不是文件，我们会在后面的章节详细探讨），但是这个文件却不在了，所以将其设置为"deleted b.txt"的状态

![git的文件状态和索引](https://ynkonghao.github.io/img/git/02/10.png)

此时由于index是通过文件的内容来检索的，所以我们只要把c.txt添加到Staged中，index就会发现没有任何的变化，所以又回到了`Unmodified`状态。

![git的文件状态和索引](https://ynkonghao.github.io/img/git/02/11.png)

这就是如何从`Unmodified`状态回到`Untracked`状态，如果我们执行的rm命令不带--cached

```
git rm b.txt
```
此时会把文件中的b.txt和git库中的都删除，就没有`Untracked`状态的文件。

![git的文件状态和索引](https://ynkonghao.github.io/img/git/02/12.png)

这就是git的四种状态和状态之间的转换，了解这个是学习git原理的基础。希望大家能够把这个知识搞懂。
