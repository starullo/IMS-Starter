package com.qa.ims.persistence.domain;

public class OrderedItem {
	
	private String company;
	private String product;
	private double price;
	private int quantity;
	
	public OrderedItem(String company, String product, double price, int quantity) {
		this.company = company;
		this.product = product;
		this.price = price;
		this.quantity = quantity;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {

		return "company: " + this.getCompany() + ", product: " + this.getProduct() + ", price: " + "$" + this.getPrice() + ", quantity: " + this.getQuantity();
	}
	
	
	
	
}
