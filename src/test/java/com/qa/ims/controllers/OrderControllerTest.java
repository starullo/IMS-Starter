package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderedItem;
import com.qa.ims.utils.Utils;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;

	@Test
	public void createTest() {
		OrderController controller = new OrderController(dao, utils);
		Long customerId = 12L;
		
		final Order o = new Order(customerId);
		
		Mockito.when(utils.getLong()).thenReturn(customerId);
		Mockito.when(dao.create(o)).thenReturn(o);

		assertEquals(o, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(o);
	}
	
	@Test void addToOrderTest() {
		OrderController controller = new OrderController(dao, utils);
		Long customerId = 5L;
		Long orderId = 12L;
		
		final Order o = new Order(orderId, customerId);
		
		Mockito.when(utils.getLong()).thenReturn(orderId, 1L);
		Mockito.when(utils.getString()).thenReturn("1");
		Mockito.when(dao.addItem(1L, orderId, 1)).thenReturn(o);
		
		Order added = controller.addToOrder();
		
		assertEquals(orderId, added.getId());
		
		
	}
	
	@Test
	public void getTotalTest() {
		Order o = new Order(1L, 1L, LocalDate.of(2020, 1, 1));
		
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(dao.getTotal(1L)).thenReturn(new BigDecimal("19.99"));
		Mockito.when(dao.read(1L)).thenReturn(o);
		
		assertEquals("19.99", controller.getTotal() + "");
	}
	
	@Test
	public void readItemsTest() {
		Long orderId = 15L;
		Long customerId = 22L;
		Long itemId1 = 12L;
		Long itemId2 = 13L;
		Order o = new Order(orderId, customerId, LocalDate.of(2021, 1, 14));
		OrderedItem oi1 = new OrderedItem(itemId1, "Wilson", "Volleyball", 19.99, 1);
		OrderedItem oi2 = new OrderedItem(itemId2, "Spaulding", "Basketball", 14.99, 1);
		ArrayList<OrderedItem> itemsList = new ArrayList<OrderedItem> ();
		itemsList.add(oi1);
		itemsList.add(oi2);
		
		Mockito.when(utils.getLong()).thenReturn(orderId);
		Mockito.when(dao.readItems(orderId)).thenReturn(itemsList);
		
		assertEquals(itemsList, controller.readItems());
	}
	
	@Test
	public void readTest() {
		Order o = new Order(213L, 4L, LocalDate.of(2021, 6, 1));
		
		Mockito.when(utils.getLong()).thenReturn(213L);
		Mockito.when(dao.read(213L)).thenReturn(o);
		
		assertEquals(o, controller.read());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).read(213L);
		
	}

	@Test
	public void readAllTest() {
		ArrayList<Order> orders = new ArrayList<Order>();
		orders.add(new Order((long) 21, LocalDate.of(2021, 4, 25)));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void updateTest() {
		Order o = new Order((long) 22, 11L, LocalDate.of(2020, 12, 22));

		Mockito.when(this.utils.getLong()).thenReturn((long) 22, (long) 11);
		Mockito.when(this.utils.getString()).thenReturn("2020-12-22");
		Mockito.when(this.dao.update(o)).thenReturn(o);
		
		Order updated = controller.update();
		
		assertEquals(updated, o);

		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}
	
	@Test
	public void deleteItemTest() {
		Order o = new Order(13L, 3L, LocalDate.of(2021, 5, 15));
		ArrayList<OrderedItem> orders = new ArrayList<OrderedItem> ();
		OrderedItem oi1 = new OrderedItem(122L, "Wilson", "Volleyball", 19.99, 1);
		OrderedItem oi2 = new OrderedItem(444L, "Spaulding", "Basketball", 14.99, 1);
		orders.add(oi1);
		orders.add(oi2);
		
		Mockito.when(this.utils.getLong()).thenReturn(13L, 122L);
		Mockito.when(this.dao.readItems(13L)).thenReturn(orders);
		Mockito.when(this.dao.deleteItem(122L, 13L)).thenReturn(1);
		
		int res = controller.deleteItem();
		assertEquals(1, res);
	}
	
	@Test
	public void deleteTest() {
		Order o = new Order((long) 52, (long) 22, LocalDate.of(2021, 2, 9));
		
		Mockito.when(this.utils.getLong()).thenReturn((long) 52);
		Mockito.when(this.dao.delete(52)).thenReturn(1);
		
		int result = controller.delete();
		assertEquals(1, result);
		
		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).delete(52);
	}

}