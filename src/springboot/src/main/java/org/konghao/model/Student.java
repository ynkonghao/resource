package org.konghao.model;

import javax.persistence.*;

/**
 * Created by konghao on 2016/11/22.
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

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
