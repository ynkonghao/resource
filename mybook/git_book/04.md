# 第四讲GIT文件的修改和还原

当我们弄清楚GIT的几种文件状态之后，这一部分的内容就变得简单了，我们通过一个具体的例子来了解一下GIT的修改和修改还原。首先创建一个新的文件夹并且通过`git init`将其设置为git的工厂，添加一个a.txt的文件，使用`git add .`将其设置为Staged状态之后通过`git commit -m "first"`将其添加到git的版本库中。这些操作相信大家已经能够熟练的掌握了。

下一步我们修改一下a.txt文件，执行`git add .`再次将其提交到`Staged`状态，此时我们先不进行commit，我们向a.txt中写入一些内容，使用`git status`看看情况

![git的文件的修改和还原](https://ynkonghao.github.io/img/git/02/13.png)

我们发现a.txt处于两种状态`Staged`和`Modified`，这是因为我们第一次修改进行了add之后它变成了`Staged`状态，Index对这次操作进行了记录，但此时再次修改了之后，Index发现a.txt的内容和版本库中不一致，所以也将它的状态改为了`Modified`状态，此时进行commit操作。

![git的文件的修改和还原](https://ynkonghao.github.io/img/git/02/14.png)

我们发现，该文件仅仅只是提交了`Staged`状态的修改，而`Modified`状态的文件并没有进行提交，这再次证明了上一节课的内容，只有`Staged`状态才能commit到版本库中,如果是`Modified`状态，需要先通过add将其设置为`Staged`状态（也就是第一讲中所说的暂存区）。此时如果我发现我这次的修改没有意义，希望a.txt能够和版本库中的内容一致，只要使用以下命令即可

```
git checkout a.txt
```

查询一下a.txt，我们会发现该文件的内容已经和版本库中一样了。

![git的文件的修改和还原](https://ynkonghao.github.io/img/git/02/15.png)

所以通过checkout可以很方便的让自己的文件同步版本库中的内容，注意使用checkout只能同步`modified`状态下的内容，此时我们再次对a.txt进行修改，并且通过add进行操作之后，a.txt的文件状态就从`Modified`变成了`Staged`状态，我们再来使用checkout看看效果。

![git的文件的修改和还原](https://ynkonghao.github.io/img/git/02/16.png)

此时我们发现checkout并不能还原原来的文件内容了（我们将会在下一讲讲解git的三种常用组件，到时候就知道为什么无法还原了）。所以我们得到一个结论checkout命令并不能还原`Staged`状态的内容，此时可以通过

```
git reset HEAD a.txt
```
把该文件从`Staged`状态设置为`Modified`状态，也就是将这个文件从暂存区中清除。

![git的文件的修改和还原](https://ynkonghao.github.io/img/git/02/17.png)

之后通过`git checkout a.txt`就可以把内容还原了。

这节课的内容就到这里了，只要掌握了git的几种状态之后，这个内容非常好理解。下一讲将会讲解git最核心的知识，就是git最常用的三个组件`BLOB、Tree、Commit`。
