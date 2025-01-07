package com.demo.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.dao.Emp;
import com.demo.spring.dao.EmpDao;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/emp")
public class EmpRestController {

	@Autowired
	EmpDao dao;
	
	@Autowired
	EmpService empService;
	
	@GetMapping()
	public List<Emp> getall(){
		return dao.getEmpList();
	}
	
	@GetMapping(path="/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Emp> getDataStream(){
		
		return dao.getStreamData();
	}
	
	//REactive CRUD 
	
	@GetMapping(path="/list",produces =MediaType.APPLICATION_JSON_VALUE)
	public Flux<Emp> getEmpList(){
		return empService.listAll();
	}
	
	@GetMapping(path="/{id}",produces =MediaType.APPLICATION_JSON_VALUE)
	public Mono<Emp> findEmp(@PathVariable int id){
		return empService.findEmpById(id);
	}
	
	@PostMapping(path="/add",consumes =MediaType.APPLICATION_JSON_VALUE)
	public Mono<Emp> addEmp(@RequestBody Emp emp){
		return empService.save(emp);
	}
}
