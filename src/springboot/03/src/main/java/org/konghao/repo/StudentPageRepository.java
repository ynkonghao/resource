package org.konghao.repo;

import org.konghao.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by konghao on 2016/11/25.
 */
public interface StudentPageRepository extends  PagingAndSortingRepository<Student,Integer> {

    Page<Student> findByAge(int age, Pageable pageable);

    List<Student> findByAge(int age, Sort sort);
}
