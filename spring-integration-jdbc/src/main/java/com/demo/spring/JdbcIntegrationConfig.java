package com.demo.spring;

import java.time.Duration;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.jdbc.JdbcMessageHandler;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.messaging.MessageChannel;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@EnableIntegration
public class JdbcIntegrationConfig {

	@Autowired
	DataSource dataSource;

	@Bean
	public MessageChannel inputChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel outputChannel() {
		return new DirectChannel();
	}

	@Transactional()
	public JdbcPollingChannelAdapter jdbcInboundAdapter() {
		JdbcPollingChannelAdapter adapter = new JdbcPollingChannelAdapter(dataSource, "select * from EMP");
		adapter.setRowMapper((rs, rowCount) -> {
			return new Emp(rs.getInt("EMPNO"), rs.getString("NAME"), rs.getString("ADDRESS"), rs.getDouble("SALARY"));
		});
		
		return adapter;
	}
	
	@Transactional
	public JdbcMessageHandler jdbcMessageHandler() {
		String sql="insert into emp(empno, name, address, salary) values (?,?,?,?)";
		JdbcMessageHandler handler= new JdbcMessageHandler(dataSource, sql);
		handler.setPreparedStatementSetter((ps,message)->{
			Emp emp= (Emp)message.getPayload();
			ps.setInt(1, emp.getEmpId());
			ps.setString(2, emp.getName());
			ps.setString(3, emp.getCity());
			ps.setDouble(4, emp.getSalary());
		});
		return handler;
	}

	//@Bean
	public IntegrationFlow jdbcInboundFlow() {
		return IntegrationFlow
				.from(jdbcInboundAdapter(),e->e.poller(Pollers.fixedDelay(Duration.ofSeconds(3))))
				.handle(message->{
					List<Emp> empList=(List<Emp>)message.getPayload();
					empList.stream().forEach(System.out::println);})
				.get();
	}
	
	@Bean
	public IntegrationFlow jdbcOutboundFlow() {
		return IntegrationFlow
				.from("inputChannel")
				.handle(jdbcMessageHandler())
				.get();
	}
	
	
}
