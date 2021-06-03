package com.qa.ims.persistence.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class OrderedItemTest {

//	CONSTRUCTOR
	@Test
	public void constructorTest() {
		OrderedItem oi = new OrderedItem("Spaulding", "Basketball", 19.99, 1);
		assertEquals("Spaulding", oi.getCompany());
		assertTrue(oi instanceof OrderedItem);
		assertEquals(19.99, oi.getPrice());
	}
	
//	GETTERS
	@Test
	public void gettersTest() {
		OrderedItem oi = new OrderedItem("Bic", "Ball-Point Pen", 1.49, 10);
		assertEquals("Bic", oi.getCompany());
		assertEquals("Ball-Point Pen", oi.getProduct());
		assertEquals(1.49, oi.getPrice());
		assertEquals(10, oi.getQuantity());
	}
	
//	SETTERS
	@Test
	public void settersTest() {
		OrderedItem oi = new OrderedItem("GR", "KS", 1.00, 100);
		oi.setCompany("Gordon Ramsay");
		oi.setProduct("Knife Set");
		oi.setPrice(149.99);
		oi.setQuantity(1);
		assertEquals("Gordon Ramsay", oi.getCompany());
		assertEquals("Knife Set", oi.getProduct());
		assertEquals(149.99, oi.getPrice());
		assertEquals(1, oi.getQuantity());
	}
	
//	OVERRIDES
	@Test
	public void toStringTest() {
		OrderedItem oi = new OrderedItem("Wilson", "Volleyball", 19.99, 5);
		String toString = oi.toString();
		assertEquals("company: Wilson, product: Volleyball, price: 19.99, quantity: 5", toString);
	}
}
