package com.demo.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

@Configuration
@EnableTransactionManagement
public class AtomikosConfig {

    @Bean
    public UserTransactionManager userTransactionManager() throws Throwable {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.init();
        return userTransactionManager;
    }

    @Bean
    public UserTransactionImp userTransactionImp() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(300);
        return userTransactionImp;
    }

    @Bean
    public JtaTransactionManager transactionManager(UserTransactionImp userTransactionImp, UserTransactionManager userTransactionManager) throws Throwable {
        return new JtaTransactionManager(userTransactionImp, userTransactionManager);
    }
}
