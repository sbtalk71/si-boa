package com.demo.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.dao.Emp;
import com.demo.spring.dao.EmpRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
@RequestMapping("/emp")
public class EmpResource {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	EmpRepository empRepository;

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Emp> findEmp(@PathVariable Integer id) {
		Emp e = em.find(Emp.class, id);
		
		if (e != null) {
			return ResponseEntity.ok(e);
		} else {
			throw new EmpNotFoundException(id + "");
		}
	}

	@PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addEmp(@RequestBody Emp e) {
		empRepository.save(e);
		return ResponseEntity.ok("Emp saved with id " + e.getEmpId());
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleEx(RuntimeException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

}
