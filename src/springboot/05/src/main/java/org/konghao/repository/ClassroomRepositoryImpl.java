package org.konghao.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.konghao.model.Classroom;
import org.konghao.model.ClassroomDto;
import org.konghao.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class ClassroomRepositoryImpl implements ClassroomRepositoryCustom {
	
	@Autowired
    @PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void delete(int cid) {
		Classroom cla = entityManager.find(Classroom.class, cid);
		studentRepository.updateByHql("delete from Student where cid=?",cid);
		//强行抛出异常,验证声明式事务是否起作用
		//if(cid>1) throw new RuntimeException();
		entityManager.remove(cla);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassroomDto> listClassroomDto() {
		String hql = "select cla.id,cla.name,cla.grade,stu.id,stu.name,stu.age,stu.address from Classroom cla,Student stu where cla.id=stu.cid";
		List<Object[]> list = entityManager.createQuery(hql).getResultList();
		List<ClassroomDto> clas = new ArrayList<ClassroomDto>();
		ClassroomDto cd = null;
		List<Student> stus = null;
		System.out.println(list.size());
		for(Object[] objs:list) {
			Student stu = new Student();
			stu.setId((Integer)objs[3]);
			stu.setName((String)objs[4]);
			stu.setAge((Integer)objs[5]);
			stu.setAddress((String)objs[6]);
			if(!checkExist(clas,(Integer)objs[0])) {
				stus = new ArrayList<Student>();
				stus.add(stu);
				cd = new ClassroomDto();
				cd.setCid((Integer)objs[0]);
				cd.setName((String)objs[1]);
				cd.setGrade((String)objs[2]);
				cd.setStus(stus);
				if(!checkExist(clas, cd.getCid())) clas.add(cd);
			} else {
				cd.getStus().add(stu);
			}
		}
		return clas;
	}

	private boolean checkExist(List<ClassroomDto> clas, Integer id) {
		for(ClassroomDto cd:clas) {
			if(cd.getCid()==id) return true;
		}
		return false;
	}
	

}
