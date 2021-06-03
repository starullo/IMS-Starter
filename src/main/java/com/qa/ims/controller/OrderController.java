package com.qa.ims.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderedItem;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrderDAO orderDAO;
	private Utils utils;
	
	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}
	
	public Order addToOrder() {
		LOGGER.info("Please enter the id of the order you'd like to add to");
		Long orderId = utils.getLong();
		LOGGER.info("Please enter the id of the item you'd like to add to the order");
		Long itemId = utils.getLong();
		LOGGER.info("Please enter the quantity of the item you'd like to add to the order");
		String q = utils.getString();
		int quantity = Integer.parseInt(q);
		Order order = orderDAO.addItem(itemId, orderId, quantity);
		LOGGER.info("Item added to order");
		return order;
	}
	
	public ArrayList<OrderedItem> readItems() {
		LOGGER.info("Please enter the order id of the items you'd like to view");
		Long orderId = utils.getLong();
		ArrayList<OrderedItem> orderedItems = orderDAO.readItems(orderId);
		for (OrderedItem oi : orderedItems) {
			LOGGER.info(oi);
		}
		return orderedItems;
	}
	
	public String getTotal() {
		LOGGER.info("Please enter the order id to view the total");
		Long orderId = utils.getLong();
		BigDecimal t = orderDAO.getTotal(orderId);
		BigDecimal displayVal = t.setScale(2, RoundingMode.HALF_EVEN);
		LOGGER.info(orderDAO.read(orderId));
		LOGGER.info("$" + displayVal);
		return displayVal + "";
	}
	
	public int deleteItem() {
		LOGGER.info("Please enter the id of the order you'd like to modify");
		Long orderId = utils.getLong();
		ArrayList<OrderedItem> orderedItems = orderDAO.readItems(orderId);
		for (OrderedItem oi : orderedItems) {
			LOGGER.info(oi);
		}
		LOGGER.info("Please enter the id of the item you'd like to remove from the order");
		Long itemId = utils.getLong();
		return orderDAO.deleteItem(itemId, orderId);
	}

	
	@Override
	public Order create() {
		LOGGER.info("Please enter the id of the customer making the order");
		Long customerId = utils.getLong();
		Order order = orderDAO.create(new Order(customerId));
		LOGGER.info("Order created");
		LOGGER.info(order);
		return order;
	}
	
	public Order read() {
		LOGGER.info("Please enter the id of the order you'd like to view");
		Long id = utils.getLong();
		Order order = orderDAO.read(id);
		LOGGER.info(order);
		return order;
	}
	
	@Override
	public ArrayList<Order> readAll() {
		ArrayList<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}
	
	
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you'd like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter the id of the customer making the order");
		Long customerId = utils.getLong();
		LOGGER.info("Please enter the date the order was placed (ex. \"YYYY-MM-DD\")");
		String date = utils.getString();
		String[] dateArr = date.split("-", 0);
//		PUT EXCEPTION HANDLERS HERE!!!!!!!!!
		int year = Integer.parseInt(dateArr[0]);
		int month = Integer.parseInt(dateArr[1]);
		int day = Integer.parseInt(dateArr[2]);
		Order order = new Order(id, customerId, LocalDate.of(year, month, day));
		Order updated = orderDAO.update(order);
		LOGGER.info("Item Updated");
		return updated;
	}
	
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

}
