package com.revature.service;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.exception.BankRegistrationException;
import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.repository.BankRepository;
import com.revature.repository.BankRepositoryJdbc;

public class BankServiceImpl implements BankService {
	
	private static Logger LOGGER = Logger.getLogger(BankService.class);
	
	private BankRepository repository = new BankRepositoryJdbc();
	
	//Account methods

	@Override
	public boolean registerAccount(Account account) {
		boolean wasSuccessful = repository.insert(account);
		if(!wasSuccessful) {
			throw new BankRegistrationException("Invalid account");
		}
		return wasSuccessful;
	}

	@Override
	public Set<Account> getAllAccounts() {
		return repository.findAllAccounts();
	}

	@Override
	public Set<Account> getAccountsByType(String accountType) {

		return repository.findByAccountType(accountType);
	}

	@Override
	public Account getAccount(Long accountNumber) {
		
		return repository.findByAccountNumber(accountNumber);
	}
	
	//Customer methods

	@Override
	public boolean registerCustomer(Customer customer) {
		boolean wasSuccessful = repository.insert(customer);
		if(!wasSuccessful) {
			throw new BankRegistrationException("Invalid customer");
		}
		return wasSuccessful;
	}
	@Override
	public Set<Customer> getAllCustomers() {
		
		return repository.findAllCustomers();
	}
	
	@Override
	public Set<Customer> getCustomersByAccountType(Account accountType) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<Customer> getCustomersByAccountStatus(Account accountStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomer(Long id) {
		
		return repository.findByCustomerId(id);
	}
	
	

}
