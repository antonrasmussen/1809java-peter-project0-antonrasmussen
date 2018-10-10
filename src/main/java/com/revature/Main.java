package com.revature;

import com.revature.controller.Controller;

/** 
 * Project 0: Banking Project JDBC
 * 
 * @author Anton Rasmussen
 * 
 * Implemented Required Functionalities:
	 * A user can login and logout.
	 * A user can view their balance.
	 * A user can withdraw money.
	 * A user can deposit money.
	 * 
 * Implemented Optional Functionalities:
     * Itemized Account Balances
     * Itemized Withdrawal
     * Itemized Deposit
 */
public class Main {

	public static void main(String[] args) {
				
		Controller bankController = new Controller();
		
		//> Control access to banking application
		//  with login name validation
		bankController.unvalidatedMenu();
	}
}
