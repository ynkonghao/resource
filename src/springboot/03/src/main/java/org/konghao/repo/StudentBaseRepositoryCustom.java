package org.konghao.repo;

import java.util.List;

/**
 * Created by konghao on 2016/12/5.
 */
public interface StudentBaseRepositoryCustom {
    //基于原生态的sql进行查询
    List<Object[]> groupByStudentAsSql();
    //基于Hibernate的HQL进行查询
    List<Object[]> groupByStudentAsHql();
    //基于Specification的方式进行查询，使用的是CriteriaQuery进行查询
    List<Object[]> groupByStudentAsSpecification();
}
