package com.revature.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.exception.BankRegistrationException;
import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.service.BankService;
import com.revature.service.BankServiceImpl;

public class AccountRegistrationTest {



	private BankService bankService;

	//Mock object (for good case)
	private Account testAccount;

	//Mock object (for good case)
	private Account testAccountWrong;

	//This is where the MOCKITO would go
	@Before
	public void setUp() {
		bankService = new BankServiceImpl();
		testAccount = new Account(
				null,
				"SAVINGS", 
				"ACTIVE", 
				420.00, 
				new Customer(421L),
				null);

		testAccountWrong = new Account(
				0L, 
				null, 
				"INACTIVE", 
				500.00, 
				new Customer(111L),
				null);
	}

	@Test
	public void registrationSuccess() {
		assertTrue(bankService.registerAccount(testAccount));
	}

	@Test(expected = BankRegistrationException.class)
	public void registrationFailure() {
		bankService.registerAccount(testAccountWrong);
	}

	@After
	public void tearDown() {
		bankService = null;
		testAccount = null;
	}
}
