package com.revature.service;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.model.Account;
import com.revature.model.Customer;

public class BankServiceImpl implements BankService {
	
	private static Logger LOGGER = Logger.getLogger(BankService.class);

	@Override
	public boolean registerAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerAccountSecure(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Account> getAccount(String accountType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccount(Long accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean registerCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerCustomerSecure(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Customer> getCustomer(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomer(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
