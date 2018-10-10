package com.revature.service;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.exception.BankRegistrationException;
import com.revature.exception.CustomerLoginException;
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
	public Set<Account> getAccountsByType(String accountType) {

		return repository.findByAccountType(accountType);
	}

	@Override
	public Account getAccount(Long accountNumber) {
		
		return null; //repository.findByAccountNumber(accountNumber);
	}
	
	//Customer methods

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
	public Set<Customer> getCustomersByAccountType(Account accountType) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<Customer> getCustomersByAccountStatus(Account accountStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean bankHasLoginName(String loginName) {
		//LOGGER.info("In bankHasLoginName");
		boolean wasSuccessful = repository.isValidLoginName(loginName);
		if(!wasSuccessful){
			
			LOGGER.info("Not a valid login name... throwing exception....");
			//throw new CustomerLoginException("Login Name Not Recognized.");
		}
		return wasSuccessful;
		
	}
	
	public double getAccountBalanceByCustomerId(Long customerId) {
		LOGGER.info("In getAccountBalanceByCustomerId");
		double accountBalance = repository.findBalanceByCustomerId(customerId);
		
		return accountBalance;
	}
	
	public double getCombinedAccountBalanceByLoginName(String loginName) {
		//LOGGER.info("In getAccountBalanceByLogin");
		double accountBalance = repository.findTotalBalanceByLoginName(loginName);
		
		return accountBalance;
	}
	
	public double getSingleAccountBalanceByLoginNameAndAccountNumber(String loginName, Long accountNumber) {
		double accountBalance = repository.findSingleBalanceByLoginNameAndAccountNumber(loginName, accountNumber);
		
		return accountBalance;
	}
	
	public Set<Long> getAllAccountNumbers(String loginName) {
		return repository.findAccountNumbersByLoginName(loginName);
	}
	
	public Set<String> getAllAccountTypes(String loginName) {
		return repository.findAccountTypesByLoginName(loginName);
	}
	
	public void setNewAccountBalance(double accountBalance, Long accountNumber) {
		repository.updateBalance(accountBalance, accountNumber);
	}
	
	public static void main(String[] args) {
		//LOGGER.info(new BankRepositoryJdbc().findAllAccounts());
		//LOGGER.info(new BankRepositoryJdbc().findAllCustomers());
		//LOGGER.info(new BankRepositoryJdbc().findByLoginName());		
		//LOGGER.info(new BankRepositoryJdbc().isValidLoginName("TestLogin1"));
		//LOGGER.info(new BankRepositoryJdbc().findBalanceByLoginName("anton"));
	}
	
	

}
