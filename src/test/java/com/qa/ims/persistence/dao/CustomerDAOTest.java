package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOTest {

	private final CustomerDAO DAO = new CustomerDAO();

	@BeforeEach
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Customer CREATED = new Customer("chris", "perrins");
		final Customer CREATED2 = DAO.create(CREATED);
		CREATED.setId(CREATED2.getId());
		assertEquals(CREATED, CREATED2);
		Customer c2 = new Customer((long) 1, "John", "Smith");
		Customer added2 = DAO.create(c2);
		Customer c3 = new Customer((long) 1, "John", "Smith");
		c3.setFirstName(null);
		c3.setSurname(null);
		Customer added3 = DAO.create(c3);
		Customer n = null;
		Customer added4 = DAO.create(n);
		assertNotEquals(added2, added3);
		assertNotEquals(added4, 15);
	}

	@Test
	public void readAllTest() {
		ArrayList<Customer> rows = DAO.readAll();
		for (Customer c : rows) {
			assertTrue(c instanceof Customer);
		}
	}

	@Test
	public void testReadLatest() {
		Customer c = new Customer("Person", "Example");
		Customer addedCustomer = DAO.create(c);
		c.setId(addedCustomer.getId());
		Customer latest = DAO.readLatest();
		assertEquals(c, latest);
	}

	@Test
	public void testRead() {
		Customer c = new Customer("Wayne", "Campbell");
		Customer addedCustomer = DAO.create(c);
		c.setId(addedCustomer.getId());
		Customer readCustomer = DAO.read(addedCustomer.getId());
		assertEquals(c, readCustomer);
		DAO.read((long) -111111);
	}

	@Test
	public void testUpdate() {
		Customer c = new Customer("Lucy", "Ricardo");
		Customer addedCustomer = DAO.create(c);
		Customer updatedCustomer = new Customer("Ricky", "Ricardo");
		updatedCustomer.setId(addedCustomer.getId());
		Customer updatedCustomerReturned = DAO.update(updatedCustomer);
		assertNotEquals("Lucy", updatedCustomerReturned.getFirstName());
		assertEquals("Ricky", updatedCustomerReturned.getFirstName());
		assertEquals("Ricardo", updatedCustomerReturned.getSurname());
		assertEquals(addedCustomer.getId(), updatedCustomerReturned.getId());
		Customer n = null;
		Customer returned = DAO.update(n);
		Customer c2 = new Customer((long) -1111111, "John", "Smith");
		assertNotEquals(returned, 123);
		assertNotEquals(c2, 5555);

	}

	@Test
	public void testDelete() {
		Customer c = new Customer("Person", "Example");
		Customer addedCustomer = DAO.create(c);
		int result = DAO.delete(addedCustomer.getId());
		assertEquals(1, result);
		assertNotEquals(0, result);
		int res = DAO.delete(-92834098324L);
		assertNotEquals(1, res);
	}
}
