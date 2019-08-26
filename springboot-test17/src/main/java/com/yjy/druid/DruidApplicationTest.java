package com.yjy.druid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjy.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class DruidApplicationTest {

	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testConnect() throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement ps = connection.prepareStatement("select 1 from dual");
		ResultSet resultSet = ps.executeQuery();
		while(resultSet.next()) {
			System.out.println("测试：" + resultSet.getInt(1));
		}
	}
}
