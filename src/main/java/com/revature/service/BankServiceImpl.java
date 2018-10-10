package com.revature.service;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.exception.BankRegistrationException;
import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.repository.BankRepository;
import com.revature.repository.BankRepositoryJdbc;

public class BankServiceImpl implements BankService {
	
	private static Logger LOGGER = Logger.getLogger(BankServiceImpl.class);
	
	private BankRepository repository = new BankRepositoryJdbc();
	

	@Override
	public boolean registerAccount(Account account) {
		LOGGER.info("In register Account");
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
	public boolean registerCustomer(Customer customer) {
		LOGGER.info("In register Customer");
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
	public boolean bankHasLoginName(String loginName) {
		boolean wasSuccessful = repository.isValidLoginName(loginName);
		if(!wasSuccessful){
			
			LOGGER.info("Not a valid login name...");
		}
		return wasSuccessful;
		
	}
	
	@Override
	public double getAccountBalanceByCustomerId(Long customerId) {
		
		double accountBalance = repository.findBalanceByCustomerId(customerId);
		
		return accountBalance;
	}
	
	@Override
	public double getCombinedAccountBalance(String loginName) {
		
		double accountBalance = repository.findTotalBalanceByLoginName(loginName);
		
		return accountBalance;
	}
	
	@Override
	public double getSingleAccountBalance(String loginName, Long accountNumber) {
		double accountBalance = repository.findSingleBalanceByLoginNameAndAccountNumber(loginName, accountNumber);
		
		return accountBalance;
	}
	
	@Override
	public Set<Long> getAllAccountNumbers(String loginName) {
		return repository.findAccountNumbersByLoginName(loginName);
	}
	
	@Override
	public Set<String> getAllAccountTypes(String loginName) {
		return repository.findAccountTypesByLoginName(loginName);
	}
	
	@Override
	public void setNewAccountBalance(double accountBalance, Long accountNumber) {
		repository.updateBalance(accountBalance, accountNumber);
	}
	
	public static void main(String[] args) {
		LOGGER.info(new BankRepositoryJdbc().findAllAccounts());
		LOGGER.info(new BankRepositoryJdbc().findAllCustomers());
		LOGGER.info(new BankRepositoryJdbc().findTotalBalanceByLoginName("anton"));		
		LOGGER.info(new BankRepositoryJdbc().isValidLoginName("anton"));
		LOGGER.info(new BankRepositoryJdbc().findTotalBalanceByLoginName("anton"));
	}
	
	

}
