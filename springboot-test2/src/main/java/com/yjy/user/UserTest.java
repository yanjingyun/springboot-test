package com.yjy.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjy.core.repository.BaseSqlDao;
import com.yjy.user.domain.User;
import com.yjy.user.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	
	@Autowired
	private BaseSqlDao baseSqlDao;

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testAdd() { //新增
		User user1 = new User("testAA1", "123", Date.valueOf("1991-01-01"));
		User user2 = new User("testAA2", "123", Date.valueOf("1992-02-02"));
		User user3 = new User("testAA3", "123", Date.valueOf("1993-03-03"));
		User user4 = new User("testAA4", "123", Date.valueOf("1994-04-04"));

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);
		
	}
	
	@Test
	public void testDelete() { //删除
		String userId = "297ea8576c1c8605016c1c860fdd0003"; //id不存在对应的user时会报错
		userRepository.deleteById(userId);
	}
	
	@Test
	public void testUpdate() { //更新
		User user = userRepository.findById("297ea8576c40a6d2016c40a6df240000").get();
		user.setUsername("testUpdate");
		userRepository.save(user);
	}
	
	@Test
	public void testFindAll() { //查询所有
		List<User> list = userRepository.findAll();
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testSelect2() { //查询Id
		String userId = "297ea8576c1c8605016c1c860f4d0011"; //id不存在对应的user时会报错
		User user = userRepository.findById(userId).get(); //方法一
		System.out.println(user);

		User one = userRepository.getOne(userId); //方法二
		System.out.println(one);
	}
	
	@Test
	public void testPageData() { //创建分页数据，为底下两个查询做准备
		for (int i = 1; i < 24; i++) {
			User user = new User("testPage" + i, "123", Date.valueOf("1994-05-" + i));
			userRepository.save(user);
		}
	}
	
	@Test
	public void testSelect3() { //根据动态条件查询
		List<User> list = userRepository.findAll((root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			// 测试and条件：where t.username like ? and t.birthday = ?
			Predicate p1 = builder.like(root.get("username"), "testPage" + "%");
			Predicate p2 = builder.equal(root.get("birthday"), Date.valueOf("1994-05-10"));
			Predicate and = builder.and(p1, p2);
			predicates.add(and);
			
			/*// 测试or条件： where t.username like ? or username = ?
			Predicate p1 = builder.like(root.get("username"), "testPage" + "%");
			Predicate p2 = builder.equal(root.get("username"), "testAA1");
			Predicate or = builder.or(p1, p2);
			predicates.add(or);*/
			
			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		});
		System.out.println(list.size());
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testSelect4() { //根据动态条件查询+分页+排序
		int pageNumber = 2; //当前页
		int pageSize = 10; //每页显示数
//		Pageable request = PageRequest.of(pageIndex, pageSize); //不排序
		
		Sort sort = new Sort(Direction.DESC, "birthday"); //排序
		Pageable request = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<User> page = userRepository.findAll((root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			predicates.add(builder.like(root.get("username"), "testPage" + "%")); //动态传值
			
			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		}, request);
		
		System.out.println("当前页数：" + page.getNumber());
		System.out.println("每页显示数：" + page.getSize());
		System.out.println("总页数：" + page.getTotalPages());
		System.out.println("总记录数：" + page.getTotalElements());
		// 总数据为23条，测试如下 0：1~10 1：11~20 2：21~23 3：无数据输出
		List<User> list = page.getContent();
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void getSingleResult() {
		String sql = "select count(*) as count from t_user t where t.username = :username and t.birthday = :birthday";
		Map<String, Object> filter = new HashMap<>();
		filter.put("username", "testUpdate");
		filter.put("birthday", Date.valueOf("1992-02-02"));
		
		Object object = baseSqlDao.getSingleResult(sql, filter);
		System.out.println(object.toString());
	}
	
	@Test
	public void getSingleResult2() {
		String sql = "select * from t_user t where t.username = :username and t.birthday = :birthday";
		Map<String, Object> filter = new HashMap<>();
		filter.put("username", "testUpdate");
		filter.put("birthday", Date.valueOf("1992-02-02"));
		
		User user = baseSqlDao.getSingleResult(sql, User.class, filter);
		System.out.println(user);
	}
	
	@Test
	public void getResultList() {
		// 注： “*”可替换成需要的字段，这样能按照顺序查询出来，如:t.username,t.birthday
		String sql = "select * from t_user t where t.username = :username and t.birthday = :birthday";
		Map<String, Object> filter = new HashMap<>();
		filter.put("username", "testUpdate");
		filter.put("birthday", Date.valueOf("1992-02-02"));
		
		List<Object[]> list = baseSqlDao.getResultList(sql, filter);
		for (Object[] objects : list) {
			for (Object object : objects) {
				System.out.print(object.toString() + " ");
			}
			System.out.println();
		}
	}

	@Test
	public void getResultList2() {
		// 注： “*”可替换成需要的字段，这样能按照顺序查询出来，如:t.username,t.birthday
		String sql = "select * from t_user t where t.username = :username and t.birthday = :birthday";
		Map<String, Object> filter = new HashMap<>();
		filter.put("username", "testUpdate");
		filter.put("birthday", Date.valueOf("1992-02-02"));
		
		List<User> list = baseSqlDao.getResultList(sql, User.class, filter);
		for (User user : list) {
			System.out.println(user);
		}
	}

	@Test
	public void getResultPage() {
		String sql = "select * from t_user t where t.username like concat('%', :username, '%')";
		Map<String, Object> filter = new HashMap<>();
		filter.put("username", "testPage");
		
		int pageNumber = 2;
		int pageSize = 10;
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Object[]> page = baseSqlDao.getResultPage(sql, pageable, filter);
		System.out.println("当前页数：" + page.getNumber());
		System.out.println("每页显示数：" + page.getSize());
		System.out.println("总页数：" + page.getTotalPages());
		System.out.println("总记录数：" + page.getTotalElements());
		for (Object[] objects : page.getContent()) {
			for (Object object : objects) {
				System.out.print(object.toString() + " ");
			}
			System.out.println();
		}
	}
}
