package com.demo.spring.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public class EmpDao {

	public void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	public List<Emp> getEmpList(){
		
		return IntStream
				.rangeClosed(1, 30)
				.peek(i->sleep())
				.peek(i->System.out.println("Processing Emp "+i))
				.mapToObj(i->new Emp(i, "Emp"+i, "Address"+i, i+20000))
				.collect(Collectors.toList());
	}
	
	public Flux<Emp> getStreamData(){
		
		return Flux
				.range(1, 30)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i->System.out.println("Processing EMp "+i))
				.map(i->new Emp(i, "Emp"+i, "Address"+i, i+20000));
	}
}
