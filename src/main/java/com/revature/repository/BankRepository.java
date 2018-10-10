package com.revature.repository;

import java.util.Set;

import com.revature.model.Account;
import com.revature.model.Customer;

public interface BankRepository {
		
	/*
	 * Insert operations
	 */
	
	/**
	 * Register a new account.
	 * 
	 * @param account
	 * @return If the registration was successful
	 */
	boolean insert(Account account);
	
	/**
	 * Register a new customer.
	 * 
	 * @param customer
	 * @return If the registration was successful
	 */
	boolean insert(Customer customer);

	/**
	 * Updates account balance with deposit or withdrawal.
	 * 
	 * @param accountBalance
	 * @param accountNumber
	 * @return If the balance was updated successfully
	 */
	boolean updateBalance(double accountBalance, Long accountNumber);
	
	
	/*
	 * Read operations
	 */
	
	/**
	 * Fetches all existing accounts.
	 * 
	 * @return Set<Account>
	 */
	Set<Account> findAllAccounts();
	
	/**
	 * Fetches a specific account by loginName and accountNumber.
	 * 
	 * @param loginName
	 * @param accountNumber
	 * @return an Account
	 */
	Account findByAccountNumber(String loginName, long accountNumber);

	
	/**
	 * Fetches all existing customers.
	 * 
	 * @return Set<Customer>
	 */
	Set<Customer> findAllCustomers();
	
	/**
	 * Returns a customer's balance by their id, which means
	 * a sum of all accounts under one customer id
	 * 
	 * @param id
	 * @return double balance total by customer id
	 */	
	double findBalanceByCustomerId(long id);
	
	
	/**
	 * Returns a balance across all of a customer's accounts
	 * 
	 * @param loginName
	 * @return combined account balance
	 */
	double findTotalBalance(String loginName);
	
	
	/**
	 * Returns a balance for a specified account
	 * 
	 * @param loginName
	 * @param accountNumber
	 * @return single account balance
	 */
	double findSingleBalance(String loginName, Long accountNumber);
	
	
	/**
	 * Helper function for finding account numbers
	 * 
	 * @param loginName
	 * @return set of account numbers
	 */
	Set<Long> findAccountNumbersByLoginName(String loginName);

    /**
     * Helper function for finding account types
     * 
     * @param loginName
     * @return set of account types
     */
	Set<String> findAccountTypesByLoginName(String loginName);
	
	/**
	 * Fetch a customer by their login name
	 * 
	 * @param loginName
	 * @return a single set 
	 * TODO: look at refactoring the return
	 */
	Set<Customer> findByLoginName(String loginName);
	
	/**
	 * Checks to see if the provided login name exists in the database
	 * @param loginName
	 * @return true or false
	 */
	boolean isValidLoginName(String loginName);
	
	

}
