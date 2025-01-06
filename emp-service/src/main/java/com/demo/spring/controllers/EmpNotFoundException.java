package com.demo.spring.controllers;

public class EmpNotFoundException extends RuntimeException {

	public EmpNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public EmpNotFoundException(String id) {
		super(id);
	}

}
