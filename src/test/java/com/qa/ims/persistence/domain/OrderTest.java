package com.qa.ims.persistence.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.time.LocalDate;



public class OrderTest {

//	CONSTRUCTORS
	@Test
	public void constructorTest1() throws ParseException {
		LocalDate date1 = LocalDate.of(2021, 4, 25);
		Order order = new Order((long) 2, (long) 43, date1);
		assertEquals(2, order.getId());
		assertEquals(43, order.getCustomerId());
		assertEquals("2021-04-25", order.getOrderedOnString());
	}
	
	@Test
	public void constructorTest2() {
		LocalDate date = LocalDate.of(2021, 1, 12);
		Order order = new Order((long) 234, (long) 21, date);
		assertEquals(234, order.getId());
		assertEquals(21, order.getCustomerId());
		assertEquals("2021-01-12", order.getOrderedOnString());
	}
	
	@Test
	public void constructorTest3() {
		Order o = new Order((long) 22, LocalDate.of(2020, 12, 22));
		assertNull(o.getId());
		Order o2 = new Order((long) 23, LocalDate.of(2020, 1, 2));
		assertNull(o.getId());
	}
	
	@Test
	public void constructorTest4() {
		Order o = new Order((long) 234);
		Order o2 = new Order(4L, 3L);
		o.setOrderedOn(LocalDate.of(2020, 11, 5));
		o2.setOrderedOn("2020-12-4");
		assertEquals(o.getCustomerId(), 234L);
		assertEquals(o2.getId(), 4L);
		assertEquals(o2.getCustomerId(), 3L);
		assertEquals("2020-11-05", o.getOrderedOnString());
		assertEquals("2020-12-04", o2.getOrderedOnString());
	}
	
//	SETTERS
	@Test
	public void settersTest() {
		LocalDate date = LocalDate.of(2020, 12, 2);
		Order order = new Order((long) 41, (long) 3, date);
		assertEquals(41, order.getId());
		order.setId((long) 9);
		assertEquals(9, order.getId());
		assertEquals(3, order.getCustomerId());
		order.setCustomerId(((long) 79));
		assertEquals(79, order.getCustomerId());
		assertEquals("2020-12-02", order.getOrderedOnString());
		LocalDate date2 = LocalDate.of(2020, 5, 5);
		order.setOrderedOn(date2);
		assertEquals("2020-05-05", order.getOrderedOnString());
	}
	
//	OVERRIDES
	@Test
	public void toStringTest() {
		LocalDate date = LocalDate.of(2021, 5, 15);
		Order order = new Order((long) 87329, (long) 349, date);
		assertEquals("id: 87329, customerId: 349, orderedOn: 2021-05-15", order.toString());
	}
	
	@Test
	public void equalsTest() {
		LocalDate date = LocalDate.of(2020, 8, 14);
		Order order = new Order((long) 1, (long) 159, date);
		LocalDate date2 = LocalDate.of(2020, 11, 14);
		Order order2 = new Order((long) 432, (long) 414, date2);
		assertFalse(order.equals(order2));
		Order order3 = order2;
		assertTrue(order2.equals(order3));
		LocalDate ordOn3 = LocalDate.of(2020, 6, 6);
		Order order4 = new Order((long) 20432, (long) 414, ordOn3);
		assertFalse(order3.equals(order4));
		LocalDate ordOn4 = LocalDate.of(2021, 1, 11);
		Order order5 = new Order((long) 843, (long) 414, ordOn4);
		assertFalse(order4.equals(order5));
		Order order6 = new Order((long) 843, (long) 414, ordOn4);
		assertTrue(order5.equals(order6));
		Boolean res1 = order5.equals(null);
		Item i = new Item("Spaulding", "Basketball", 19.99);
		Boolean res2 = order5.equals(i);
		order.setCustomerId(null);
		assertFalse(order.equals(order2));
		order2.setCustomerId(null);
		assertFalse(order.equals(order2));
		order.setId(null);
		assertFalse(order.equals(order2));
		order2.setId(null);
		assertFalse(order.equals(order2));
		Order o1 = new Order(9L, 9L);
		Order o2 = new Order(9L, 9L);
		o1.setId(null);
		assertFalse(o1.equals(o2));
	}
}
