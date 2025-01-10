package com.demo.spring;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories
@EntityScan("com.demo.spring") // Adjust to your package
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create().driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1").username("sa").password("").build();
    }

	/*
	 * @Bean public JpaTransactionManager transactionManager(EntityManagerFactory
	 * emf) { return new JpaTransactionManager(emf); }
	 * 
	 * @Bean public LocalContainerEntityManagerFactoryBean
	 * entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource
	 * dataSource) { return builder .dataSource(dataSource)
	 * .packages("com.demo.spring") // Adjust package .persistenceUnit("default")
	 * .build(); }
	 */
}
