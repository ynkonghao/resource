package org.konghao.jpa.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.konghao.model.Student;
import org.konghao.repo.StudentCrudRepository;
import org.konghao.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	//注入刚才定义的接口
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentCrudRepository studentCrudRepository;

	@Test
	public void testStudent() {
		Assert.assertEquals("foo",studentRepository.findById(1).getName());
		Assert.assertEquals("foo",studentRepository.readById(1).getName());
		Assert.assertEquals(1,studentRepository.getById(1).size());
		Assert.assertEquals("foo",studentRepository.loadById(1).getName());
		Assert.assertEquals(2,studentRepository.findByAddressAndAge("zt",22).size());
	}

	@Test
	public void testAddStudent() {
		//添加操作
		Student stu = new Student("foo1","km",22);
		studentCrudRepository.save(stu);
	}

	@Test
	public void testUpdateStudent() {
		/*修改的操作*/
		Student stu = studentCrudRepository.findOne(1);
		stu.setName("bar1");
		studentCrudRepository.save(stu);
	}

	@Test
	public void testDelete() {
		//删除操作
		studentCrudRepository.delete(1);
	}

	@Test
	public void testCount() {
		//取数量操作
		Assert.assertEquals(3,studentCrudRepository.count());
		Assert.assertEquals(2,studentCrudRepository.countByAge(22));
	}

}
