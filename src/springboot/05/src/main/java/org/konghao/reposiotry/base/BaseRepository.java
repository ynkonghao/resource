package org.konghao.reposiotry.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by konghao on 2016/12/7.
 */
@NoRepositoryBean
@Transactional(readOnly=true)
public interface BaseRepository<T,ID extends Serializable> extends JpaRepository<T,ID> {
    public List<Object[]> listBySQL(String sql);
    
    @Transactional
    public void updateBySql(String sql,Object...args);
    @Transactional
    public void updateByHql(String hql,Object...args);
}
