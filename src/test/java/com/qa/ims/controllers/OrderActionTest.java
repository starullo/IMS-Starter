package com.qa.ims.controllers;

import org.junit.jupiter.api.Test;

import com.qa.ims.controller.OrderAction;

import static org.junit.jupiter.api.Assertions.*;

public class OrderActionTest {
	
	@Test
	public void orderActionTest() {
		int count = 0;
		for (OrderAction oa : OrderAction.values()) {
			count += 1;
		}
		assertEquals(9, count);
	}
}
