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
	boolean registerAccount(Account account);
	boolean registerAccountSecure(Account account);
	Set<Account> getAllAccounts();
	Set<Account> getAccount(String accountType);
	Account getAccount(Long accountNumber);
	
	// Customer Stuff
	boolean registerCustomer(Customer customer);
	boolean registerCustomerSecure(Customer customer);
	Set<Customer> getAllCustomers();
	Set<Customer> getCustomer(String firstName, String lastName);
	Customer getCustomer(Long id);	

}
