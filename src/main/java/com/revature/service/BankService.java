package com.revature.service;

import java.util.Set;

import com.revature.model.Account;
import com.revature.model.Customer;

/**
 * The BankService class provides functionality for authentication, 
 * validation, and registration.
 * 
 * 
 * @author Anton Rasmussen
 *
 */

public interface BankService {
	
	// Account Stuff
	
	/**
	 * Register a new account
	 * 
	 * @param account
	 * @return if the count was registered successfully
	 */
	boolean registerAccount(Account account);
	
	/**
	 * Get a set of all the accounts in the Account Table
	 * 
	 * @return Set<Account>
	 */
	Set<Account> getAllAccounts();
	
	/**
	 * Get a set of accounts by account type
	 * (Checking, Savings, or Custodial)
	 * @param accountType
	 * @return a set of accounts based on type
	 */
	Set<Account> getAccountsByType(String accountType);
	
	/**
	 * Get a single account by account number
	 * 
	 * @param accountNumber
	 * @return a single account
	 */
	Account getAccount(Long accountNumber);
	
	// Customer Stuff
	
	/**
	 * Register a new customer
	 * 
	 * @param customer
	 * @return if the customer was registered successfully
	 */
	boolean registerCustomer(Customer customer);
	
	/**
	 * Get a set of all customers
	 * 
	 * @return all customers
	 */
	Set<Customer> getAllCustomers();
	
	/**
	 * Get a set of customers with all the same account type
	 * 
	 * @param accountType
	 * @return customers by account type
	 */
	Set<Customer> getCustomersByAccountType(Account accountType);
	
	/**
	 * Get a set of customers with all the same account status
	 * 
	 * @param accountStatus
	 * @return customers by account status
	 */
	Set<Customer> getCustomersByAccountStatus(Account accountStatus);
	
	
	/**
	 * Check to see if loginName is in database
	 * @param loginName
	 * @return true or false
	 */
	boolean bankHasLoginName(String loginName);
	
	
	/**
	 * Get the account balance based on customerId; this
	 * will be the sum of balances across all accounts for one customer
	 * 
	 * @param customerId
	 * @return double accountBalance (total across all accounts)
	 */
	public double getAccountBalance(Long customerId);

}
