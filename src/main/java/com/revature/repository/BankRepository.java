package com.revature.repository;

import java.util.Set;

import com.revature.model.Account;
import com.revature.model.Customer;

public interface BankRepository {
		
	/*
	 * Insert operations
	 */
	
	/**
	 * Register a new account
	 * 
	 * @param account
	 * @return If the registration was successful
	 */
	boolean insert(Account account);
	
	/**
	 * Register a new customer
	 * 
	 * @param customer
	 * @return If the registration was successful
	 */
	boolean insert(Customer customer);
	
	
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
	 * Fetches all existing customers.
	 * 
	 * @return Set<Customer>
	 */
	Set<Customer> findAllCustomers();
	
	/**
	 * Returns an account based on its accountNumber 
	 * (there is only one or none)
	 * @param accountNumber
	 * @return Set<Account
	 */	
	//Account findByAccountNumber(long accountNumber);
	
	/**
	 * Returns a customer's balance by their id, which means
	 * a sum of all accounts under one customer id
	 * @param id
	 * @return double balance total by customer id
	 */	
	double findBalanceByCustomerId(long id);
	
	double findSingleBalanceByLoginNameAndAccountNumber(String loginName, Long accountNumber);
	
	double findTotalBalanceByLoginName(String loginName);

	
	/**
	 * Returns a group of accounts depending on the accountType
	 * @param accountType
	 * @return Set<Account>
	 */
	Set<Account> findByAccountType(String accountType);
	
	double depositToAccount(Account accountNumber);
	
	Set<Long> findAccountNumbersByLoginName(String loginName);
	
	Set<String> findAccountTypesByLoginName(String loginName);
	
	public Account findByAccountNumber(String loginName, long accountNumber);
	
	/**
	 * Returns customer(s) based on firstName and Last name
	 * (Can contain duplicates)
	 * @param String firstName, String lastName
	 * @return Set<Customer>
	 */	
	Set<Customer> findByName(String firstName, String lastName);
	
	/**
	 * Returns a customer when given a login name
	 *
	 * @return A customer or an error
	 */
	Set<Customer> findByLoginName(String loginName);
	
	/**
	 * Checks to see if the provided login name exists in the database
	 * @param loginName
	 * @return true or false
	 */
	boolean isValidLoginName(String loginName);
	
	boolean updateBalance(double accountBalance, Long accountNumber);
	
	

}
