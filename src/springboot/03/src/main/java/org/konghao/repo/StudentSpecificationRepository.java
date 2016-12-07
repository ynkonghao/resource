package org.konghao.repo;

import org.konghao.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by konghao on 2016/11/28.
 */
public interface StudentSpecificationRepository extends JpaRepository<Student,Integer>,
        JpaSpecificationExecutor<Student>{
}
