package com.demo.spring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class EmpExceptionHandler {

	@ExceptionHandler(EmpNotFoundException.class)
	public ResponseEntity<Mono<String>> handleEx1(EmpNotFoundException ex){
		return ResponseEntity.status(404).body(Mono.just(ex.getMessage()));
	}
	
	@ExceptionHandler(EmpExistsException.class)
	public ResponseEntity<Mono<String>> handleEx2(EmpExistsException ex){
		return ResponseEntity.status(500).body(Mono.just(ex.getMessage()));
	}
}
