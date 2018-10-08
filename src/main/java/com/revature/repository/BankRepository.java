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
	
	/**
	 * It should use a CallableStatement and a stored procedure.
	 * @param account
	 * @return If the registration was successful
	 */
	boolean insertProceedure(Account account);
	
	/**
	 * It should use a CallableStatement and a stored procedure.
	 * @param customer
	 * @return If the registration was successful
	 */
	boolean insertProceedure(Customer customer);
	
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
	Account findByAccountNumber(long accountNumber);
	
	/**
	 * Returns a customer based on its id (there is only one or none)
	 * @param id
	 * @return Set<Customer>
	 */	
	Customer findByCustomerId(long id);
	
	/**
	 * Returns a group of accounts depending on the accountType
	 * @param accountType
	 * @return Set<Account>
	 */
	Set<Account> findByAccountType(String accountType);
	
	//
	//TODO: Add more methods here for various ways to search
	//
	
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
	Set<Customer> findByLoginName();
	
	/**
	 * Checks to see if the provided login name exists in the database
	 * @param loginName
	 * @return true or false
	 */
	boolean isValidLoginName(String loginName);
	
	

}
