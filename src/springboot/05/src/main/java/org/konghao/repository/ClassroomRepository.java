package org.konghao.repository;

import java.util.List;

import org.konghao.model.Classroom;
import org.konghao.model.ClassroomStuNumDto;
import org.konghao.reposiotry.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ClassroomRepository extends BaseRepository<Classroom, Integer>,ClassroomRepositoryCustom, JpaSpecificationExecutor<Classroom> {

	@Query("select new org.konghao.model.ClassroomStuNumDto(cla.id,cla.name,cla.grade,count(stu.id)) from Classroom cla ,Student stu where cla.id=stu.cid group by cla.id")
	public List<ClassroomStuNumDto> listClassrooms();
}
