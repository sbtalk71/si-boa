package com.demo.spring.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import com.demo.spring.dao.Emp;

@Component
public class JdbcClientDemo implements CommandLineRunner {

	@Autowired
	DataSource dataSource;

	@Override
	public void run(String... args) throws Exception {

		JdbcClient jdbcClient = JdbcClient.create(dataSource);
		
	Emp e=	jdbcClient
		.sql("select * from emp where empno=?")
		.param(1,100)
		.query((rs,count)->
		{return new Emp(rs.getInt("empno"),rs.getString("name"),rs.getString("address"),rs.getDouble("salary"));
			
		}).single();

	List<Emp> empList=	jdbcClient
			.sql("select * from emp")
			//.param(1,100)
			.query((rs,count)->
			{return new Emp(rs.getInt("empno"),rs.getString("name"),rs.getString("address"),rs.getDouble("salary"));
				
			}).list();
	
	empList.stream().forEach(em->System.out.println(em.getName()));
	}

}
