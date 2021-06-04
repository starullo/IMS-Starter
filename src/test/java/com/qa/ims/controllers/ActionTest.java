package com.qa.ims.controllers;

import org.junit.jupiter.api.Test;

import com.qa.ims.controller.Action;

import static org.junit.jupiter.api.Assertions.*;

public class ActionTest {
	
	@Test
	public void actionTest() {
		int count = 0;
		for (Action oa : Action.values()) {
			count += 1;
		}
		assertEquals(5, count);
	}
}

