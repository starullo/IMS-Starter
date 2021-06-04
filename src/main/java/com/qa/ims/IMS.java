package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.controller.Action;
import com.qa.ims.controller.CrudController;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrderAction;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

public class IMS {

	public static final Logger LOGGER = LogManager.getLogger();

	private final CustomerController customers;
	private final ItemController items;
	private final OrderController orders;
	private final Utils utils;

	public IMS() {
		this.utils = new Utils();
		final CustomerDAO CUSTDAO = new CustomerDAO();
		final ItemDAO ITEMDAO = new ItemDAO();
		final OrderDAO ORDERDAO = new OrderDAO();
		this.customers = new CustomerController(CUSTDAO, utils);
		this.items = new ItemController(ITEMDAO, utils);
		this.orders = new OrderController(ORDERDAO, utils);
	}

	public void imsSystem() {
		LOGGER.info("Welcome to the Inventory Management System!");
		DBUtils.connect();

		Domain domain = null;
		do {
			LOGGER.info(" ");
			LOGGER.info("Which entity would you like to use?");
			LOGGER.info(" ");
			Domain.printDomains();
			LOGGER.info(" ");
			domain = Domain.getDomain(utils);

			domainAction(domain);

		} while (domain != Domain.STOP);
	}

	private void domainAction(Domain domain) {
		boolean changeDomain = false;
		do {

			CrudController<?> active = null;
			switch (domain) {
			case CUSTOMER:
				active = this.customers;
				break;
			case ITEM:
				active = this.items;
				break;
			case ORDER:
				active = this.orders;
				break;
			case STOP:
				return;
			default:
				break;
			}
			
			LOGGER.info(" ");
			LOGGER.info(() ->"What would you like to do with " + domain.name().toLowerCase() + ":");

			if (active == this.orders) {
				OrderAction.printActions();
				OrderAction oa = OrderAction.getOrderAction(utils);
				if (oa == OrderAction.RETURN) {
					changeDomain = true;
				} else {
					doAction(this.orders, oa);
				}
			} else {
			Action.printActions();
			Action action = Action.getAction(utils);
			

			if (action == Action.RETURN) {
				changeDomain = true;
			} else {
				doAction(active, action);
			}
			}
		} while (!changeDomain);
	}

	public void doAction(CrudController<?> crudController, Action action) {
			switch (action) {
			case CREATE:
				crudController.create();
				break;
			case READ:
				crudController.readAll();
				break;
			case UPDATE:
				crudController.update();
				break;
			case DELETE:
				crudController.delete();
				break;
			case RETURN:
				break;
			default:
				break;
			
		}
	}
	
	public void doAction(OrderController oc, OrderAction oi) {
			switch (oi) {
			case CREATE:
				oc.create();
				break;
			case ADD:
				oc.addToOrder();
				break;
			case TOTAL:
				oc.getTotal();
				break;
			case READ:
				oc.readAll();
				break;
			case ITEMS:
				oc.readItems();
				break;
			case UPDATE:
				oc.update();
				break;
			case REMOVE:
				oc.deleteItem();
				break;
			case DELETE:
				oc.delete();
				break;
			case RETURN:
				break;
			default:
				break;
			}
		}
	

}
