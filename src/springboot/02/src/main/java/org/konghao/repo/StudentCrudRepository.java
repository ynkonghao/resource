package org.konghao.repo;

import org.konghao.model.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by konghao on 2016/11/24.
 */
public interface StudentCrudRepository extends CrudRepository<Student,Integer>{
    //增加了一个countByXX的方法
    public long countByAge(int age);
}
