package org.konghao.jpa.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.konghao.model.Student;
import org.konghao.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	//注入刚才定义的接口
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentCrudRepository studentCrudRepository;

	@Autowired
	private StudentPageRepository studentPageRepository;

    @Autowired
    private StudentSpecificationRepository studentSpecificationRepository;

	@Autowired
	private StudentBaseRepository studentBaseRepository;

	@Autowired
	private StudentExtendsRepository studentExtendsRepository;

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

	@Test
	public void testPage() {
		//显示第1页每页显示3条
		PageRequest pr = new PageRequest(1,3);
		//根据年龄进行查询
		Page<Student> stus = studentPageRepository.findByAge(22,pr);
		Assert.assertEquals(2,stus.getTotalPages());
		Assert.assertEquals(6,stus.getTotalElements());
		Assert.assertEquals(1,stus.getNumber());
	}

	@Test
	public void testSort() {
        //设置排序方式为name降序
        List<Student> stus = studentPageRepository.findByAge(22
                ,new Sort(Sort.Direction.DESC,"name"));
        Assert.assertEquals(5,stus.get(0).getId());

        //设置排序以name和address进行升序
        stus = studentPageRepository.findByAge(22
                ,new Sort(Sort.Direction.ASC,"name","address"));
        Assert.assertEquals(8,stus.get(0).getId());

        //设置排序方式以name升序，以address降序
        Sort sort = new Sort(
                        new Sort.Order(Sort.Direction.ASC,"name"),
                        new Sort.Order(Sort.Direction.DESC,"address"));

        stus = studentPageRepository.findByAge(22,sort);
        Assert.assertEquals(7,stus.get(0).getId());
    }

    @Test
    public void testSpecificaiton1() {
        List<Student> stus = studentSpecificationRepository.findAll(new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                //root.get("address")表示获取address这个字段名称,like表示执行like查询,%zt%表示值
                Predicate p1 = criteriaBuilder.like(root.get("address"), "%zt%");
                Predicate p2 = criteriaBuilder.greaterThan(root.get("id"),3);
                //将两个查询条件联合起来之后返回Predicate对象
                return criteriaBuilder.and(p1,p2);
            }
        });
        Assert.assertEquals(2,stus.size());
        Assert.assertEquals("oo",stus.get(0).getName());
    }

    @Test
    public void testSpecificaiton2() {
		//第一个Specification定义了两个or的组合
		Specification<Student> s1 = new Specification<Student>() {
			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate p1 = criteriaBuilder.equal(root.get("id"),"2");
				Predicate p2 = criteriaBuilder.equal(root.get("id"),"3");
				return criteriaBuilder.or(p1,p2);
			}
		};
		//第二个Specification定义了两个or的组合
		Specification<Student> s2 = new Specification<Student>() {
			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate p1 = criteriaBuilder.like(root.get("address"),"zt%");
				Predicate p2 = criteriaBuilder.like(root.get("name"),"foo%");
				return criteriaBuilder.or(p1,p2);
			}
		};
		//通过Specifications将两个Specification连接起来，第一个条件加where，第二个是and
		List<Student> stus = studentSpecificationRepository.findAll(Specifications.where(s1).and(s2));

        Assert.assertEquals(1,stus.size());
        Assert.assertEquals(3,stus.get(0).getId());
    }

    @Test
    public void testCustomRepository() {
		List<Object[]> list1 = studentBaseRepository.groupByStudentAsSql();
		Assert.assertEquals(2,list1.size());
		Assert.assertEquals("km",list1.get(0)[0]);


		List<Object[]> list2 = studentBaseRepository.groupByStudentAsHql();
		Assert.assertEquals(2,list2.size());
		Assert.assertEquals("km",list2.get(0)[0]);

		List<Object[]> list3 = studentBaseRepository.groupByStudentAsSpecification();
		Assert.assertEquals(1,list3.size());
		Assert.assertEquals("zt",list3.get(0)[0]);
	}

	@Test
	public void testBaseRepository() {
		//直接使用BaseRepository中的方法
		List<Object[]> list = studentExtendsRepository.listBySQL("select address,count(*) from t_student group by address");
		Assert.assertEquals(2,list.size());
		Assert.assertEquals("km",list.get(0)[0]);
		//原JpaRepository的方法依然可以使用
		List<Student> list2 = studentExtendsRepository.findByNameAndAddress("bar","zt");
		Assert.assertEquals(1,list2.size());
	}

}
