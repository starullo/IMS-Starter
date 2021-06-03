package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderedItem;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	
	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long customerId = resultSet.getLong("customer_id");
		String orderedOn = resultSet.getString("ordered_on");
		String [] dateArr = orderedOn.split("-", 0);
		int year = Integer.parseInt(dateArr[0]);
		int month = Integer.parseInt(dateArr[1]);
		int date = Integer.parseInt(dateArr[2]);
		
		
		return new Order(id, customerId, LocalDate.of(year, month, date));
	}
	
	public OrderedItem modelOIFromResultSet(ResultSet resultSet) throws SQLException {
		String company = resultSet.getString("items.company");
		String product = resultSet.getString("items.product");
		double price = resultSet.getDouble("items.price");
		int quantity = resultSet.getInt("ordered_items.quantity");
		return new OrderedItem(company, product, price, quantity);
	}
	
	public ArrayList<OrderedItem> readItems(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT items.company, items.product, items.price, ordered_items.quantity FROM ordered_items JOIN items ON items.id = ordered_items.item_id WHERE order_id = ?");) {
			ArrayList<OrderedItem> items = new ArrayList<OrderedItem>();
			while (resultSet.next()) {
				items.add(modelOIFromResultSet(resultSet));
			}
			return items;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<OrderedItem>();
	}
	
	public Order addItem(Long itemId, Long orderId, int quantity) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO ordered_items(item_id, order_id, quantity) VALUES (?, ?, ?)");) {
			statement.setLong(1, itemId);
			statement.setLong(2, orderId);
			statement.setInt(3, quantity);
			statement.executeUpdate();
			return read(orderId);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public int deleteItem(Long itemId, Long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM ordered_items WHERE item_id = ? AND order_id = ?");) {
			statement.setLong(1, itemId);
			statement.setLong(2, orderId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	public double getTotal(Long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT items.company, items.product, items.price, ordered_items.quantity FROM ordered_items JOIN items ON items.id = ordered_items.item_id WHERE order_id = ?");) {
			statement.setLong(1,  orderId);
			ResultSet resultSet = statement.executeQuery();
			ArrayList<OrderedItem> items = new ArrayList<OrderedItem>();
			double total = 0.0;
			while (resultSet.next()) {
				items.add(modelOIFromResultSet(resultSet));
			}
			for (OrderedItem oi : items) {
				total += (oi.getPrice() * oi.getQuantity());
			}
			return total;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0.0;
	}
	
	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(customer_id, ordered_on) VALUES (?, ?)");) {
			statement.setString(1, order.getCustomerId() + "");
			statement.setString(2, LocalDate.now() + "");
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public Order read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public ArrayList<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			ArrayList<Order> orders = new ArrayList<Order>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<Order>();
	}
	
	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders SET customer_id = ?, ordered_on = ? WHERE id = ?");) {
			statement.setLong(1, order.getCustomerId());
			statement.setString(2, order.getOrderedOnString());
			statement.setLong(3, order.getId());
			statement.executeUpdate();
			return read(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
}
