package com.demo.ws;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class Calculator {

	public int add(int a, int b) {
		return a + b;
	}

	public static void main(String[] args) {

		Endpoint.publish("http://localhost:8181/cal", new Calculator());
	}

}
