package com.qa.ims.persistence.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

//	CONSTRUCTORS
	@Test
	public void constructorTest1() {
		Customer c = new Customer((long) 5, "Bob", "Smith");
		assertEquals(5, c.getId());
		assertEquals("Bob", c.getFirstName());
		assertEquals("Smith", c.getSurname());
	}
	
	@Test
	public void constructorTest2() {
		Customer c = new Customer("Al", "Johnson");
		assertNull(c.getId());
		assertEquals("Al", c.getFirstName());
		assertEquals("Johnson", c.getSurname());
	}
	
//	SETTERS
	@Test
	public void settersTest() {
		Customer c = new Customer("Jack", "Robinson");
		assertNull(c.getId());
		c.setId((long) 23);
		assertEquals(23, c.getId());
		assertEquals("Jack", c.getFirstName());
		c.setFirstName("Jackie");
		assertEquals("Jackie", c.getFirstName());
		assertEquals("Robinson", c.getSurname());
		c.setSurname("Biggs");
		assertEquals("Biggs", c.getSurname());
	}
	
//	OVERRIDES
	@Test
	public void toStringTest() {
		Customer c = new Customer("Will", "Johnson");
		String s = "id:null first name:Will surname:Johnson";
		assertEquals(s, c.toString());
	}
	
	@Test void hashCodeTest() {
		Customer c = new Customer(1L, "Jessie", "Jones");
		Customer c2 = new Customer(2L, "Bobby", "James");
		c2.setFirstName(null);
		c2.setSurname(null);
		c2.setId(null);
		assertNotEquals(c.hashCode(), c2.hashCode());
	}
	
	@Test void equalsTest() {
		Customer c1 = new Customer((long) 3, "James", "Jackson");
		Customer c2 = new Customer((long) 4, "Susie", "Jones");
		assertFalse(c1.equals(c2));
		
		Customer c3 = new Customer("Pete", "Ratburn");
		Customer c4 = new Customer("Chuck", "Robinson");
		assertFalse(c3.equals(c4));
		c3.setFirstName("Chuck");
		assertFalse(c3.equals(c4));
		c3.setSurname("Robinson");
		assertTrue(c3.equals(c4));
		assertTrue(c1.equals(c1));
		Object o = null;
		assertFalse(c1.equals(o));
		Item i = new Item("Acme", "Pen", .99);
		assertFalse(c1.equals(i));
		c4.setFirstName(null);
		assertFalse(c4.equals(c1));
		c3.setFirstName(null);
		c3.setSurname("Smith");
		assertFalse(c4.equals(c3));
		c3.setId(null);
		assertFalse(c3.equals(c4));
		Customer c5 = new Customer(98L, "James", "Smith");
		Customer c6 = new Customer(98L, "James", "Smith");
		c5.setId(null);
		assertFalse(c5.equals(c6));
		c6.setId(null);
		assertTrue(c5.equals(c6));
		c5.setId(134L);
		c6.setId(1L);
		assertFalse(c5.equals(c6));
		c6.setId(134L);
		assertTrue(c5.equals(c6));
		c5.setSurname(null);
		assertFalse(c5.equals(c6));
		c6.setSurname(null);
		assertTrue(c5.equals(c6));
		
	}

}
