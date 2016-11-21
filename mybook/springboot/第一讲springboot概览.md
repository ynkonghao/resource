# 第一讲springboot概览
在使用了一段时间的springboot之后，今天决定开始弄一套完整的springboot的教程，说实话在学习springboot之前，个人已经开始厌倦了代码的编写和视频教程的录制，因为每次录制视频教程那怕一个简单的hello world都不得不面对大量的配置文件，一个helloworld测试程序，业务逻辑只是几秒钟的事情，但是却要花上十多分钟来进行配置。现在有了springboot之后，一切都变得简单了，这部分我们会使用几种方式让大家看到springboot是如何让一个项目变得如此简单的。

在开始学习springboot之前，这里依然有一些话需要提前给大家说一下，前段时间一个好朋友给我完整的讲解了springboot，在整个交流的过程中，我们不断重复的一句话是这个功能我们也实现过，那个功能我们也封装过，没错，在原来进行项目开发的过程中为了重用很多东西都是我们为了方便封装过的，但这些封装在每次开发过程中都会让人有不尽人意的感觉，如今我们总算有机会体验一下世界顶级的公司为我们封装的框架了。我相信你只要使用了springboot之后，你就会感觉到站在巨人的肩膀上向前走是多么幸福的一件事情。但是这里依然要强调一件事，在学习springboot之前，必须把原有的spring的一套原理（AOP和IOC）和MVC架构学习透彻，因为springboot已经把最底层的东西全部实现了，此时如果你对刚才提及的原理不清楚的话，在使用过程中遇到问题你将会束手无策，springboot不是我们走捷径的工具，而是在掌握原理之后帮助我们快速开发的利器。如果对这些知识点有疑问的朋友，可以下载我原来录制的教程(MVC等设计模式讲解，由于目前百度网盘已经无法使用，可以通过网络下载或者通过QQ[64831031]联系我获取这部分的教程。)，该教程虽然有些老，但是原理都讲清楚了，有兴趣的朋友可以找来看看。

废话不说了，这里我们开始正式进入springboot的第一讲的教程，我们先用相对复杂的方式搭建一个springboot的项目看看，目前STS和Intellij IDEA都能对支持springboot的开发，我们这里使用后者,另外在机器上还得安装好jdk，maven等工具，jdk我使用的版本是8，maven是3.3.9。

首先我们创建一个maven的项目，在这里就不再累赘介绍如何创建一个maven项目了，如果你连maven都不会，建议你先把maven学习了之后再来使用springboot。创建完成项目之后我们需要在`pom.xml`中加入如下的配置

```xml
<groupId>org.konghao</groupId>
<artifactId>hello-springboot</artifactId>
<version>1.0-SNAPSHOT</version>

<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>1.4.2.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>

<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <java.version>1.8</java.version>
</properties>

<dependencies>
    <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```
我们的配置就这么结束了，想想原来我们所做的事情吧，拷贝各种依赖，配置spring的xml，配置spring MVC的xml，配置web.xml，现在已经由springboot帮助我们完成了，看一看我们的依赖包，springboot需要的依赖包都已经加进来了，而且我们都不需要为依赖加任何的版本信息，因为继承了`spring-boot-starter-parent`，它已经会自动帮助我们选择最理想的版本。一切都变得简单了。

![springboot概览](https://ynkonghao.github.io/img/springboot/01/01.png)

接下来我们要创建一个class，注意这class必须在我们的groupId的包下（注意:也不能在任何子包下,springboot会从这个位置去找依赖。），我的groupId是org.konghao，所以我们创建的class的包名是org.konghao.DemoApplication

![springboot概览](https://ynkonghao.github.io/img/springboot/01/02.png)

接着写一个main方法

``` java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }
}

```
我们的项目就搭建完成了，注意需要在这个class上增加一个`SpringBootApplication`的Annotation，这说明该class是一个springboot的运行程序。我们运行一下这个class看看效果

```
...
 s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (https)
2016-11-21 11:15:23.254  INFO 8420 --- [           main] org.konghao.example.DemoApplication      : Started DemoApplication in 6.943 seconds (JVM running for 7.63)
```
项目已经顺利启动了，而且还是通过web的方式启动了,而且是在8080端口上，我们的一个项目就这样配置完成了，有没有发现是不是非常的简单，在浏览器中访问一下这个端口，我们看到如下一个错误的页面。

![springboot概览](https://ynkonghao.github.io/img/springboot/01/03.png)

这是因为我们没有写任何的视图，接下来我们通过原有springmvc的知识写一个HelloController

``` java
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by konghao on 2016/11/21.
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    public String hello() {
        return "hello springboot";
    }
}
```

这里我们和原有的SpringMVC的区别在于把`@Controller`换成了`@RestController`，因为如果使用`@controller`需要设置一个视图，在springboot之中已经不建议使用jsp了，而是使用`thymeleaf,freemarker`等模板引擎作为视图，使用`@RestController`说明这个Controller中的所有的方法返回的是一个JSON的字符串，这样我们就不需要指定专门的视图了。再次运行main函数。

![springboot概览](https://ynkonghao.github.io/img/springboot/01/04.png)

springboot已经正常工作了，整个流程变成非常的简单，甚至不需要我们进行任何多余的配置，接下来我们看更方便的一件事，我们首先在pom.xml中加入一个插件

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```
我们使用maven打一下包,`mvn package`,此时我们会得到一个hello-springboot-xx.jar的包

![springboot概览](https://ynkonghao.github.io/img/springboot/01/05.png)

把这个包拷贝到任意一个目录中，在命令提示符中使用java -jar xxx.jar这个jar包看看效果

![springboot概览](https://ynkonghao.github.io/img/springboot/01/06.png)

大家看不需要任何环境，web项目就启动起来了，并且可以通过浏览器访问了，我们甚至都没有设置和容器相关的东西，因为springboot在这个jar文件中已经把Tomcat集成进去了，大家有没有发现一切都变得如此的容易和简单。

到这里我们第一部分的内容差不多结束了，但是这里还要给大家讲一个更方便的工具，如果你记不住springboot的文件夹结构这些，spring提供一个`SPRING INITIALIZR`帮助我们快速搭建springboot的项目，`SPRING INITIALIZR `在STS中是天然集成的。在Intellij中需要收费的版本才能使用，但是我们可以通过[http://start.spring.io/](http://start.spring.io)可以直接访问。

![springboot概览](https://ynkonghao.github.io/img/springboot/01/07.png)

 点击`Generate Project`之后会得到一个hello的压缩文件夹，解压就可得到完整的springboot的目录结构，我们的第一个Application和pom文件都已经写好了。

 ![springboot概览](https://ynkonghao.github.io/img/springboot/01/08.png)

 这就是我们第一次课的内容，相信大家已经看到了Springboot的强悍之处了。
 [本文源码](https://github.com/ynkonghao/resource/tree/master/src/springboot)
