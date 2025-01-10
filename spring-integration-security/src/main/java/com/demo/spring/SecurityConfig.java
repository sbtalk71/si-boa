package com.demo.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth->auth.requestMatchers("/api/**")
				.authenticated()
				.anyRequest().permitAll()).httpBasic(Customizer.withDefaults());
		http.csrf(c->c.disable());
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager=new InMemoryUserDetailsManager();
		
		manager.createUser(User.withUsername("shantanu").password(passwordEncoder().encode("welcome1")).roles("OTHERS").build());
		manager.createUser(User.withUsername("admin").password(passwordEncoder().encode("welcome1")).roles("ADMIN").build());
		manager.createUser(User.withUsername("user1").password(passwordEncoder().encode("welcome1")).roles("USER").build());
		return manager;
	}
	
}
