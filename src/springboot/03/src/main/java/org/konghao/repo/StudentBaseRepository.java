package org.konghao.repo;

import org.konghao.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by konghao on 2016/12/6.
 */
public interface StudentBaseRepository extends JpaRepository<Student,Integer>
        ,StudentBaseRepositoryCustom {

}
