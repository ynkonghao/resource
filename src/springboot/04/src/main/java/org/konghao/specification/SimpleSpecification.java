package org.konghao.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by konghao on 2016/12/15.
 */
public class SimpleSpecification<T> implements Specification<T> {

    /**
     * 查询的条件列表，是一组列表
     * */
    private List<SpecificationOperator> opers;

    public SimpleSpecification(List<SpecificationOperator> opers) {
        this.opers = opers;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        int index = 0;
        //通过resultPre来组合多个条件
        Predicate resultPre = null;
        for(SpecificationOperator op:opers) {
            if(index++==0) {
                resultPre = generatePredicate(root,criteriaBuilder,op);
                continue;
            }
            Predicate pre = generatePredicate(root,criteriaBuilder,op);
            if(pre==null) continue;
            if("and".equalsIgnoreCase(op.getJoin())) {
                resultPre = criteriaBuilder.and(resultPre,pre);
            } else if("or".equalsIgnoreCase(op.getJoin())) {
                resultPre = criteriaBuilder.or(resultPre,pre);
            }
        }
        return resultPre;
    }

    private Predicate generatePredicate(Root<T> root,CriteriaBuilder criteriaBuilder, SpecificationOperator op) {
        /*
        * 根据不同的操作符返回特定的查询*/
        if("=".equalsIgnoreCase(op.getOper())) {
            System.out.println(op.getKey()+","+op.getValue());
            return criteriaBuilder.equal(root.get(op.getKey()),op.getValue());
        } else if(">=".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.ge(root.get(op.getKey()), (Number)op.getValue());
        } else if("<=".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.le(root.get(op.getKey()),(Number)op.getValue());
        } else if(">".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.gt(root.get(op.getKey()),(Number)op.getValue());
        } else if("<".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.lt(root.get(op.getKey()),(Number)op.getValue());
        } else if(":".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.like(root.get(op.getKey()),"%"+op.getValue()+"%");
        } else if("l:".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.like(root.get(op.getKey()),op.getValue()+"%");
        } else if(":l".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.like(root.get(op.getKey()),"%"+op.getValue());
        } else if("null".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.isNull(root.get(op.getKey()));
        } else if("!null".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.isNotNull(root.get(op.getKey()));
        } else if("!=".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.notEqual(root.get(op.getKey()),op.getValue());
        }
        return null;
    }

}
