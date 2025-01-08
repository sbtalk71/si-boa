package com.demo.spring.ex2;

public class Order {

	private String category;
	private String itemName;
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Order(String category, String itemName) {
		super();
		this.category = category;
		this.itemName = itemName;
	}


	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
}
