package com.qa.ims.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;

/**
 * Action is a collection of commands which are used to determine the type of
 * function to apply to an entity.
 *
 */
public enum OrderAction {
	CREATE("To add a new order into the database"), READ("To read an order from the database"), UPDATE("To change an order in the database"), DELETE("To remove an order from the database"), ADD("To add an item to an order in the database"), TOTAL("To get the total cost for an order in the database"), ITEMS("To read all items in an order in the database"), REMOVE("To remove an item from an order in the database"), RETURN("To return to domain selection");

	public static final Logger LOGGER = LogManager.getLogger();

	private String description;

	OrderAction(String description) {
		this.description = description;
	}

	/**
	 * Describes the action
	 */
	public String getDescription() {
		return this.name() + ": " + this.description;
	}

	/**
	 * Prints out all possible actions
	 */
	public static void printActions() {
		for (OrderAction oi : OrderAction.values()) {
			LOGGER.info(oi.getDescription());
		}
	}

	/**
	 * Gets an action based on a users input. If user enters a non-specified
	 * enumeration, it will ask for another input.
	 * 
	 * @return Action type
	 */
	public static OrderAction getOrderAction(Utils utils) {
		OrderAction oi = null;
		do {
			try {
				oi = OrderAction.valueOf(utils.getString().toUpperCase());
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		} while (oi == null);
		return oi;
	}

}