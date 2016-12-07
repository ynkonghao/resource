package org.konghao.repo;

import org.konghao.model.Student;
import org.konghao.repo.base.BaseRepository;

import java.util.List;

/**
 * Created by konghao on 2016/12/7.
 */
public interface StudentExtendsRepository extends BaseRepository<Student,Integer> {
    /**
     * 原来JPARepository的方法依然可以使用*/
    List<Student> findByNameAndAddress(String name, String address);
}
