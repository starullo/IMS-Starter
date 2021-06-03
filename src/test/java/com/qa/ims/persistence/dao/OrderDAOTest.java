package com.qa.ims.persistence.dao;

import org.junit.jupiter.api.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderedItem;
import com.qa.ims.utils.DBUtils;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class OrderDAOTest {
	
	private final OrderDAO orderDAO = new OrderDAO();
	private final CustomerDAO customerDAO = new CustomerDAO();
	private final ItemDAO itemDAO = new ItemDAO();
	Customer c1 = new Customer("Bill", "Johnson");
	Customer c2 = new Customer("Suzie", "Jones");
	Customer c3 = new Customer("Johnny", "Example");
	Customer added1 = customerDAO.create(c1);
	Customer added2 = customerDAO.create(c2);
	Customer added3 = customerDAO.create(c3);
	Item item1 = new Item("Lazy-Boy", "Recliner-Blue", 299.99);
	Item item2 = new Item("Bic", "Mini Lighter- Red", 1.49);
	Item item3 = new Item("Casio", "Electric Keyboard", 199.99);
	Item item4 = new Item("Rawlings", "Baseball", 4.99);
	Item addedItem1 = itemDAO.create(item1);
	Item addedItem2 = itemDAO.create(item2);
	Item addedItem3 = itemDAO.create(item3);
	Item addedItem4 = itemDAO.create(item4);

	@BeforeEach
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	

	
	@Test
	public void createTest() {
		Order o = new Order(added1.getId());
		Order addedOrder = orderDAO.create(o);
		o.setId(addedOrder.getId());
		assertEquals(o, addedOrder);
	}
	
	@Test
	public void readTest() {
		Order i = new Order(added1.getId());
		Order order = orderDAO.create(i);
		Order testOrder = orderDAO.read(order.getId());
		assertEquals(added1.getId(), testOrder.getCustomerId());
		assertEquals(LocalDate.now(), testOrder.getOrderedOnDate());
		Order testOrder2 = orderDAO.read((long) -11111);
	}
	
	@Test
	public void readAllTest() {
		ArrayList<Order> rows = orderDAO.readAll();
		int expectedLength = rows.size();
		int actualLength = 0;
		for (Order i : rows) {
			assertTrue(i instanceof Order);
			actualLength += 1;
		}
		assertEquals(expectedLength, actualLength);
	}
	
	@Test
	public void addItemTest() {
		Order o = new Order(added3.getId());
		Order addedOrder = orderDAO.create(o);
		Order updatedOrder = orderDAO.addItem(addedItem1.getId(), addedOrder.getId(), 1);
		Order newlyUpdatedOrder = orderDAO.addItem(addedItem2.getId(), addedOrder.getId(), 2);
		double total = orderDAO.getTotal(addedOrder.getId());
		assertEquals(302.97, total);
		Order newlyUpdatedOrder2 = orderDAO.addItem(addedItem3.getId(), addedOrder.getId(), 1);
		ArrayList<OrderedItem> items = orderDAO.readItems(addedOrder.getId());
		int expectedLength = items.size();
		int actualLength = 0;
		for (OrderedItem oi : items) {
			System.out.println(oi);
			actualLength += 1;
		}
		assertEquals(expectedLength, actualLength);
	}
	
	@Test
	public void deleteItemTest() {
		Order o = new Order(added2.getId());
		Order addedOrder = orderDAO.create(o);
		Order updatedOrder1 = orderDAO.addItem(addedItem3.getId(), addedOrder.getId(), 2);
		Order updatedOrder2 = orderDAO.addItem(addedItem4.getId(), addedOrder.getId(), 1);
		int result = orderDAO.deleteItem(addedItem3.getId(), addedOrder.getId());
		assertEquals(1, result);
		ArrayList<OrderedItem> items = orderDAO.readItems(addedOrder.getId());
		assertEquals(1, items.size());
	}
	
	@Test
	public void updateTest() {
		Order o = new Order(added2.getId(), LocalDate.now());
		Order testOrder = orderDAO.create(o);
		Long orderId = testOrder.getId();
		assertEquals(testOrder.getCustomerId(), added2.getId());
		assertEquals(testOrder.getOrderedOnDate(), LocalDate.now());
		Order updated = new Order(orderId, added2.getId(),  LocalDate.of(2020, 5, 19));
		Order updatedTestOrder = orderDAO.update(updated);
		assertEquals("2020-05-19", updatedTestOrder.getOrderedOnString());
	}
	
	@Test
	public void deleteTest() {
		Order o = new Order(added3.getId(), LocalDate.now());
		Order testOrder = orderDAO.create(o);
		int deleted = orderDAO.delete(testOrder.getId());
		assertEquals(1, deleted);
		int deleted2 = orderDAO.delete(testOrder.getId());
		assertEquals(0, deleted2);
		int deleted3 = orderDAO.delete((long) -100);
		assertEquals(0, deleted3);
	}
}