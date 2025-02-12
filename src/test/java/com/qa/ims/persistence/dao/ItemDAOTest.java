package com.qa.ims.persistence.dao;

import org.junit.jupiter.api.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;

public class ItemDAOTest {
	
	private final ItemDAO itemDAO = new ItemDAO();

	@BeforeEach
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void createTest() {
		Item item = new Item("Bic", "Ball-Point Pen", "1.29");
		Item item2 = itemDAO.create(item);
		item.setId(item2.getId());
		Item n = null;
		Item returned = itemDAO.create(n);
		assertEquals(item, item2);
	}
	
	@Test
	public void readTest() {
		Item i = new Item("Mead", "100 Page Notebook- Blue", "1.49");
		Item item = itemDAO.create(i);
		Item testItem = itemDAO.read(item.getId());
		assertEquals("Mead", testItem.getCompany());
		assertEquals("100 Page Notebook- Blue", testItem.getProduct());
		assertEquals("$1.49", testItem.getPriceString());
		assertEquals(1.49, testItem.getPriceDouble());
		assertNotEquals("$1.49", testItem.getPriceDouble());
		Item testItem2 = itemDAO.read((long) -11111);
		
	}
	
	@Test
	public void readAllTest() {
		ArrayList<Item> rows = itemDAO.readAll();
		for (Item i : rows) {
			assertTrue(i instanceof Item);
		}
	}
	
	@Test
	public void updateTest() {
		Item i = new Item("Wilson", "Volleyball", "14.99");
		Item testItem = itemDAO.create(i);
		Long id = testItem.getId();
		assertEquals(testItem.getCompany(), "Wilson");
		assertEquals(testItem.getProduct(), "Volleyball");
		assertEquals(testItem.getPriceString(), "$14.99");
		Item updated = new Item(id, "Wilson", "Volleyball", "19.99");
		Item updatedTestItem = itemDAO.update(updated);
		Item n = null;
		Item returned = itemDAO.update(n);
		assertEquals(19.99, updatedTestItem.getPriceDouble());
	}
	
	@Test
	public void deleteTest() {
		Item i = new Item("Spaulding", "Basketball", "24.99");
		Item testItem = itemDAO.create(i);
		int deleted = itemDAO.delete(testItem.getId());
		assertEquals(1, deleted);
		int deleted2 = itemDAO.delete(testItem.getId());
		assertEquals(0, deleted2);
		int deleted3 = itemDAO.delete((long) -100);
		Long id = null;
		assertEquals(0, deleted3);
	}
}
