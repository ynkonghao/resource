package org.konghao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.konghao.model.Classroom;
import org.konghao.model.ClassroomDto;
import org.konghao.model.ClassroomStuNumDto;
import org.konghao.model.Student;
import org.konghao.model.StudentBadDto;
import org.konghao.model.StudentGoodDto;
import org.konghao.reposiotry.kit.SimplePageBuilder;
import org.konghao.reposiotry.kit.SimpleSortBuilder;
import org.konghao.repository.ClassroomRepository;
import org.konghao.repository.StudentRepository;
import org.konghao.specification.SimpleSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ClassroomRepository classroomRepository;
	
	
	@Test
	public void testSort() {
		List<Student> stus = studentRepository.findAll(
				new SimpleSpecificationBuilder<Student>("address", "=", "zt").generateSpecification(),
				SimpleSortBuilder.generateSort("id_d"));
		
		Assert.assertEquals(7, stus.get(0).getId());
	}

	@Test
	public void testPage() {
		//注意:page是从0开始的
		Page<Student> stus = studentRepository.findAll(
				new SimpleSpecificationBuilder<Student>("address","=","zt").generateSpecification(), 
				SimplePageBuilder.generate(0, SimpleSortBuilder.generateSort("name_d")));
		Assert.assertEquals(1, stus.getTotalPages());
		Assert.assertEquals(4, stus.getTotalElements());
		Assert.assertEquals(5, stus.getContent().get(0).getId());
		
	}
	
	@Test
	public void testDeleStu1() {
		studentRepository.deleteByCla(2);
	}
	
	@Test
	public void testDeleStu2() {
		studentRepository.updateByHql("delete from Student where cid=?", 2);
	}
	
	@Test
	public void testDeleteClassroom() {
		classroomRepository.delete(4);
	}
	
	@Test
	public void testListStu() {
		List<StudentBadDto> sds = studentRepository.listBadStu();
		Assert.assertEquals(5, sds.size());
		Assert.assertEquals(2, sds.get(0).getStu().getId());
		Assert.assertEquals(1, sds.get(0).getCla().getId());
	}
	
	@Test
	public void testListGoodStu() {
		List<StudentGoodDto> sds = studentRepository.listGoodStu();
		Assert.assertEquals(5, sds.size());
		Assert.assertEquals(2, sds.get(0).getSid());
		Assert.assertEquals(1, sds.get(0).getClaId());
	}
	
	@Test
	public void testListClassroomStuNum() {
		List<ClassroomStuNumDto> sns = classroomRepository.listClassrooms();
		Assert.assertEquals(2, sns.size());
		Assert.assertEquals(1,sns.get(0).getCid());
		Assert.assertEquals(3, sns.get(0).getSnum());
	}
	
	@Test
	public void testListClassroomDto() {
		List<ClassroomDto> clas = classroomRepository.listClassroomDto();
		for(ClassroomDto cla:clas) {
			System.out.println(cla.getName()+","+cla.getStus());
		}
		Assert.assertEquals(2, clas.size());
		Assert.assertEquals(1, clas.get(0).getCid());
		Assert.assertEquals(3, clas.get(0).getStus().size());
	}
	
	@Test
	public void testAddClassroom() {
		Classroom cla = new Classroom();
		cla.setGrade("2016");
		cla.setName("16计算机网络");
		classroomRepository.save(cla);
		
		cla = new Classroom();
		cla.setGrade("2015");
		cla.setName("15计算机网络");
		classroomRepository.save(cla);
	}

}
