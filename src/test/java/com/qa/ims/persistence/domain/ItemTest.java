package com.qa.ims.persistence.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
	
//	CONSTRUCTORS
	@Test
	public void constructorTest1() {
		Item item = new Item((long) 1, "Wilson", "Volleyball", "19.99");
		assertEquals(1, item.getId());
		assertEquals("Wilson", item.getCompany());
		assertEquals("Volleyball", item.getProduct());
		assertEquals("$19.99", item.getPriceString());
		assertNotEquals(19.99, item.getPriceString());
		assertEquals(19.99, item.getPriceDouble());
		assertNotEquals("$19.99", item.getPriceDouble());
		
		item.setId((long) 2);
		assertEquals(2, item.getId());
		item.setCompany("Spaulding");
		assertEquals("Spaulding", item.getCompany());
		item.setProduct("Football");
		assertEquals("Football", item.getProduct());
		item.setPrice(24.99);
		assertEquals("$24.99", item.getPriceString());
		assertNotEquals(24.99, item.getPriceString());
		assertEquals(24.99, item.getPriceDouble());
		assertNotEquals("$24.99", item.getPriceDouble());
		item.setPrice("24.99");
		assertEquals("$24.99", item.getPriceString());
	}
	
	@Test
	public void constructorTest2() {
		Item item = new Item((long) 3, "Sony", "Walkman", 99.99);
		assertEquals(3, item.getId());
		assertEquals("Sony", item.getCompany());
		assertEquals("Walkman", item.getProduct());
		assertEquals("$99.99", item.getPriceString());
		assertEquals(99.99, item.getPriceDouble());
		assertNotEquals(99.99, item.getPriceString());
		assertNotEquals("$99.99", item.getPriceDouble());
	}
	
	@Test void constructorTest3() {
		Item item = new Item("Bic", "Ball-Point Pen", 1.49);
		assertNull(item.getId());
	}
	
//	SETTERS
	@Test
	public void settersTest() {
		Item item = new Item((long) 1, "Acme", "Pencil", .99);
		item.setId((long) 19);
		assertEquals(19, item.getId());
		item.setCompany("ACME");
		assertEquals("ACME", item.getCompany());
		item.setProduct("Pen");
		assertEquals("Pen", item.getProduct());
		item.setPrice(1.49);
		assertEquals("$1.49", item.getPriceString());
	}
	
//	OVERRIDES
	@Test
	public void toStringTest() {
		Item item = new Item((long) 12, "Starlight", "Telescope", 999.99);
		String s = item.toString();
		assertEquals("id: 12, company: Starlight, product: Telescope, price: 999.99", s);
		assertNotEquals("id: 12, company: Starlight, product: Telescope, price: $999.99", s);
	}
	
	@Test
	public void equalsTest() {
		Item item = new Item((long) 1223, "Kleenex", "Tissue Box", 2.99);
		Customer item2 = new Customer((long) 1, "Rob", "Smith");
		Item i = item;
		assertFalse(item.equals(item2));
		assertFalse(item.equals(null));
		assertTrue(item.equals(i));
		
		Item item3 = new Item((long) 24, "Cola", "8oz Can", .79);
		assertFalse(item.equals(item3));
		
		Item item4 = new Item((long) 15, "Cola", "12oz Bottle", 1.29);
		assertFalse(item3.equals(item4));
		
		Item item5 = new Item((long) 234, item4.getCompany(), item4.getProduct(), item4.getPriceDouble() + .20);
		assertFalse(item4.equals(item5));
		
		Item item6 = new Item((long) 1234, item4.getCompany(), item4.getProduct(), item4.getPriceDouble());
		assertFalse(item6.equals(item4));
		item6.setCompany(null);
		assertFalse(item6.equals(item4));
		item4.setCompany(null);
		assertFalse(item6.equals(item4));
		item6.setProduct(null);
		assertFalse(item6.equals(item4));
		item4.setProduct(null);
		assertFalse(item6.equals(item4));
		item6.setId(null);
		assertFalse(item6.equals(item4));
		item4.setId(null);
		assertTrue(item6.equals(item4));
	}

}
