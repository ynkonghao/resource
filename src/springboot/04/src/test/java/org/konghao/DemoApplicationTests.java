package org.konghao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.konghao.model.Student;
import org.konghao.repository.StudentRepository;
import org.konghao.specification.SimpleSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void testFind() {

		/**
		 * 这里的查询表示id大于4或者name中包含a
		 * 现在我们发现在SimpleSpecificationBuilder的add或者addOr方法中返回this的好处了
		 */
		List<Student> stus = studentRepository.findAll(
				new SimpleSpecificationBuilder("id",">",4)
						.addOr("name",":","a")
						.generateSpecification());

		Assert.assertEquals(5,stus.size());

	}
}
