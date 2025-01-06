package com.demo.spring;

import org.springframework.stereotype.Service;

@Service
public class Singer implements Performer {

	@Override
	public void perform() {
		System.out.println("Singer is singing....");

	}

}
