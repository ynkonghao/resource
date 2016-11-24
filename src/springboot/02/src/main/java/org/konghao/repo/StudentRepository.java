package org.konghao.repo;

import org.konghao.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by konghao on 2016/11/23.
 */
public interface StudentRepository extends Repository<Student,Integer> {

    @Query("select s from Student s where s.id=?1")
    public Student loadById(int id);

    //根据地址和年龄进行查询
    public List<Student> findByAddressAndAge(String address, int age);
    //根据id获取对象，即可返回对象，也可以返回列表
    public Student readById(int id);
    //根据id获取列表，这里如果确定只有一个对象，也可以返回对象
    public List<Student> getById(int id);
    //根据id获取一个对象，同样也可以返回列表
    public Student findById(int id);
}