package org.konghao.repository;


import java.util.List;

import org.konghao.model.Student;
import org.konghao.model.StudentBadDto;
import org.konghao.model.StudentGoodDto;
import org.konghao.reposiotry.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by konghao on 2016/12/16.
 */
public interface StudentRepository extends BaseRepository<Student,Integer>,JpaSpecificationExecutor<Student> {

	@Modifying //说明该操作是修改类型操作，删除或者修改
	@Transactional //因为默认是readOnly=true的，这里必须自己进行声明
	@Query("delete from Student where cid=?1") //删除的语句
	public void deleteByCla(int cid);
	
	@Query("select new org.konghao.model.StudentBadDto(stu,cla) from Student stu,Classroom cla where stu.cid=cla.id")
	public List<StudentBadDto> listBadStu();
	
	@Query("select new org.konghao.model.StudentGoodDto(stu.id,stu.name,cla.id,cla.name,cla.grade) from Student stu,Classroom cla where stu.cid=cla.id")
	public List<StudentGoodDto> listGoodStu();
	
}
