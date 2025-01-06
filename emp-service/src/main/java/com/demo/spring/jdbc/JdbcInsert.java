package com.demo.spring.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

//@Component
public class JdbcInsert implements CommandLineRunner {

	@Autowired
	DataSource dataSource;

	@Override
	public void run(String... args) throws Exception {

		JdbcTemplate jt = new JdbcTemplate(dataSource);

	int count=	jt.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pst = con
						.prepareStatement("insert into emp(empno, name, address, salary) values (?,?,?,?)");
				pst.setInt(1, 102);
				pst.setString(2, "Ranjith");
				pst.setString(3, "Bangalore");
				pst.setDouble(4, 56000);
				return pst;
			}
		});
	
	System.out.println("Count = "+count);

	}
	


}
