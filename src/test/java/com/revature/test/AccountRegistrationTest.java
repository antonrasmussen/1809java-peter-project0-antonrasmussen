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

	//Mock objects (for good case)
	private Account testAccount;
	private Customer testCustomer;

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
				0.00, 
				new Customer(6L),
				null);

//		testAccountWrong = new Account(
//				null, 
//				null, 
//				"INACTIVE", 
//				0.00, 
//				new Customer(2L),
//				null);
		
//		testCustomer = new Customer(
//				null,
//				"Jane",
//				"Doe",
//				"testlogin2",
//				"jane@email",
//				null);
	}

	@Test
	public void registrationSuccessAccount() {
		assertTrue(bankService.registerAccount(testAccount));
	}
	
//	@Test
//	public void registrationSuccessCustomer() {
//		assertTrue(bankService.registerCustomer(testCustomer));
//	}


//	@Test(expected = BankRegistrationException.class)
//	public void registrationFailure() {
//		bankService.registerAccount(testAccountWrong);
//	}


	@After
	public void tearDown() {
		bankService = null;
		testAccount = null;
		//testCustomer = null;
		//testAccountWrong = null;
	}
}
