package org.konghao.repository;

import org.konghao.model.Student;
import org.konghao.reposiotry.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by konghao on 2016/12/16.
 */
public interface StudentRepository extends BaseRepository<Student,Integer>,JpaSpecificationExecutor<Student> {

}
