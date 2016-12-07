package org.konghao.repo;

import org.konghao.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by konghao on 2016/12/5.
 */
public class StudentBaseRepositoryImpl implements StudentBaseRepositoryCustom {
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> groupByStudentAsSql() {
        List<Object[]> list = entityManager
                .createNativeQuery("select address,count(*) from t_student group by address")
                .getResultList();

        return list;
    }

    @Override
    public List<Object[]> groupByStudentAsHql() {
        List<Object[]> list = entityManager
                .createQuery("select address,count(*) from Student group by address")
                .getResultList();
        return list;
    }

    @Override
    public List<Object[]> groupByStudentAsSpecification() {
        //根据地址分组查询，并且学生数量大于3的所有地址
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Student> root = query.from(Student.class);
        query.multiselect(root.get("address"),builder.count(root.get("id")))
                .groupBy(root.get("address")).having(builder.gt(builder.count(root.get("id")),3));

        return entityManager.createQuery(query).getResultList();
    }
}
