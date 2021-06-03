package com.qa.ims;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.qa.ims.controller.Action;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.utils.Utils;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class IMSTest {
	
	@Mock
	CustomerDAO customerDAO;
	
	@Mock
	Utils utils;
	
	@InjectMocks
	CustomerController cc;
	

	@Test
	public void basicIMSTest1() {
		IMS ims = new IMS();
		assertTrue(ims instanceof IMS);
	}
	

}
