package com.demo.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
//@Profile("dev")
public class JpaConfig {

	@Autowired
	DataSource ds;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lc= new LocalContainerEntityManagerFactoryBean();
		lc.setDataSource(ds);
		lc.setPackagesToScan("com.demo.spring.dao");
		lc.setPersistenceUnitName("MyDemo");
		
		HibernateJpaVendorAdapter va=new HibernateJpaVendorAdapter();
		va.setDatabase(Database.MYSQL);
		va.setShowSql(true);
		
		lc.setJpaVendorAdapter(va);
		return lc;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager tx= new JpaTransactionManager();
		tx.setEntityManagerFactory(entityManagerFactory().getObject());
		return tx;
	}
}
