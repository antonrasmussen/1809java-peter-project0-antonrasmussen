package com.revature.service;

import java.util.Set;

import com.revature.model.Account;
import com.revature.model.Customer;

/**
 * The BankService class provides functionality for authentication, 
 * validation, and registration.
 *
 */

public interface BankService {
	
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
	double getAccountBalanceByCustomerId(Long customerId);
	
	/**
	 * Get the account balance based on loginName; this
	 * will be the sum of balances across all accounts for one loginName
	 * 
	 * @param loginName
	 * @return double accountBalance (total across all accounts)
	 */
	double getCombinedAccountBalance(String loginName);
	
	/**
	 * Get the account balance based on login name and account number; 
	 * this will be an itemized balance for one account.
	 * 
	 * @param loginName
	 * @param accountNumber
	 * @return accountBalance
	 */
	double getSingleAccountBalance(String loginName, Long accountNumber);
	
	/**
	 * Data structure for storing all account numbers
	 * 
	 * @param loginName
	 * @return set of account numbers
	 */
	Set<Long> getAllAccountNumbers(String loginName);
	
	/**
	 * Data structure for storing all account types
	 * 
	 * @param loginName
	 * @return set of account types
	 */
	Set<String> getAllAccountTypes(String loginName);
	
	/**
	 * Set a new balance. Used after a withdrawal or a deposit.
	 * 
	 * @param accountBalance
	 * @param accountNumber
	 */
	void setNewAccountBalance(double accountBalance, Long accountNumber);

}
