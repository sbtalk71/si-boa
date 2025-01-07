package com.demo.spring.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmpReactiveRepository extends ReactiveCrudRepository<Emp,Integer> {

	
}
