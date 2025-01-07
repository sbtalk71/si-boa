package com.demo.spring;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.dao.Emp;
import com.demo.spring.dao.EmpReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmpService {

	@Autowired
	EmpReactiveRepository repository;
	public Flux<Emp> listAll(){
		return repository.findAll().delayElements(Duration.ofSeconds(2));
	}
	
	
	public Mono<Emp> findEmpById(int id){
		
		return repository.findById(id)
				.switchIfEmpty(Mono.error(new EmpNotFoundException("Emp Not Found")));
	}
}
