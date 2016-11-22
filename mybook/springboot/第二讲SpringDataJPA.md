# 第二讲Spring Data JPA介绍

在基本了解了springboot的运行流程之后，我们需要逐个来突破springboot的几个关键性问题，我们首先解决的是springboot访问数据库的问题。java访问数据库经历了几个阶段，第一个阶段是直接通过JDBC访问，这种方式工作量极大，而且会做大量的重复劳动，之后出现了一些现成的ORM框架，如Hibernate、Mybatis等，这些框架封装了大量的数据库的访问操作，但是我们依然要对这些框架进行二次封装。但是现在有一个Spring Data JPA，它为我们的数据库访问定义了一套非常好的规范。如下图所示：

![spring DATA JPA 介绍](https://ynkonghao.github.io/img/springboot/02/01.png)
Spring Data JPA等于在ORM只上又进行了一次封装，但具体的对数据库的访问依然要依赖于底层的ORM框架，Spring Data JPA默认是通过Hibernate实现的，接下来我们就来看看Spring Data JPA如何访问我们的数据库和如何简化我们的操作的。

### 第一步创建一个Springboot的项目，并且添加Spring Data JPA的支持
这个操作可以直接在[start.spring.io](http://start.spring.io)网站中创建，并且添加JPA的支持。这个项目我们可以考虑不使用Web。

![spring DATA JPA 介绍](https://ynkonghao.github.io/img/springboot/02/02.png)

之后创建一个项目，拷贝maven的依赖。

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>org.konghao</groupId>
    <artifactId>hello-jpa</artifactId>
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
        <!-- spring data jpa的依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```
pom.xml设置完成之后，我们会发现依赖包中有了hibernate的jar文件，这就说明spring data jpa默认就是使用hibernate框架来作为底层的ORM。

为了能够更好的演示将来可能发生的所有情况，我们定义三个对象来进行学习
![spring DATA JPA 介绍](https://ynkonghao.github.io/img/springboot/02/03.png)
Classroom和Student之间是一对多的关系，而Student和Course之间是多对多的关系。我们现在来建立这三个实体类，由于Student和Course之间是多对多的关系，我们新建一个中间对象来存储，相信有过编程经验的朋友都不太喜欢在class中创建一对多的对象，在实际的开发中使用冗余来替换掉对象关联这样在效率和维护方面都要好一些，由于Spring Data JPA对Hiberante提供的支持，我们可使用Hibernate的注释来初始化这三个对象。三个类的代码如下:

``` java
//Student
*/
/*
* 以下两个代码其实就是Hiberate声明实体的annotation
* */
@Entity
@Table(name="t_stu")
public class Student {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String address;
    //表示班级id，这里没有引入classrom的对象，而是直接设置了cid来关联(此时也没有把他设置为外键)
    private int cid;
    ....
}
```
