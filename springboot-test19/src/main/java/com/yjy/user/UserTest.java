package com.yjy.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjy.Application;
import com.yjy.user.dao.UserDao;
import com.yjy.user.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class UserTest {

	@Autowired
	private UserDao userDao;
	
	@Test
	public void testSave() {
		User user = new User("testAA1", Date.valueOf("1998-08-01"));
		userDao.save(user);
	}
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void testSave2() {
		String sql = "insert into t_user(name, birthday) values (?, ?)";
		jdbcTemplate.update(sql, "testAA222", Date.valueOf("1998-08-02"));
	}

	@Test
	public void testUpdate() {
		String sql = "update t_user set name = ? where id = ?";
		//jdbcTemplate.update(sql, "testAA2222", 2);
		jdbcTemplate.update(sql, new Object[] {"testAA2222", 2});
	}
	
	@Test
	public void testDelete() {
		String sql = "delete from t_user where id = ?";
		jdbcTemplate.update(sql, 2);
	}
	
	@Test
	public void testBatchSave() {
		String sql = "insert into t_user(name, birthday) values (?, ?)";
		
		List<Object[]> users = new ArrayList<>();
		users.add(new Object[] {"testAA1", Date.valueOf("1998-08-01")});
		users.add(new Object[] {"testAA2", Date.valueOf("1998-08-02")});
		users.add(new Object[] {"testAA3", Date.valueOf("1998-08-03")});
		
		jdbcTemplate.batchUpdate(sql, users);
	}
	
	@Test
	public void testQueryOne() {
		String sql = "select count(*) from t_user t where t.id = ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, 11); //针对结果只有一列的查询
		System.out.println(count);
	}
	
	@Test
	public void testQueryUser() {
		String sql = "select * from t_user t where t.id = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper, 1); //针对实体类的查询
		System.out.println(user);
	}
	@Test
	public void testQueryList() {
		String sql = "select * from t_user t";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		List<User> list = jdbcTemplate.query(sql, rowMapper); //针对列表
		for (User user : list) {
			System.out.println(user);
		}
	}

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Test
	public void test1() {
		String sql = "update t_user set name = :name where id = :id";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("name", "testB123");
		paramMap.put("id", 3);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}
}
