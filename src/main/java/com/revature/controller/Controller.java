package com.revature.controller;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.model.Customer;
import com.revature.service.BankServiceImpl;

/**
 * The goal of the Controller class is to interpret inputs from 
 * the user to update the model while the view displays the model. 
 * So both the view and controller have a reference to the model.
 * 
 * The view is only used to display things on request of the
 * controller. And also when the model changes.
 * 
 * NOTE: In this project, given its `console` based "view" nature, I have
 * opted to put the `view` functionality within the Controller class.
 */

public class Controller {

	public static final Logger LOGGER = Logger.getLogger(Controller.class);

	
	private BankServiceImpl bankService = new BankServiceImpl();
	private Customer customer = new Customer();

	Scanner	scanner = new Scanner(System.in);

	public Controller() {}	


	public String getLoginName() {
		return customer.getLoginName();
	}

	public void setLoginName(String loginName) {
		customer.setLoginName(loginName);
	}
	
	public void unvalidatedMenu() {
		System.out.println("============================");
		System.out.println("    Welcome to the Bank     ");
		System.out.println("============================");
		System.out.println("|                          |");
		System.out.println("| 1. Login                 |");
		System.out.println("| 2. Exit                  |");
		System.out.println("|                          |");
		System.out.println("============================");

		System.out.println();
		System.out.print("Select option: ");
		

		int option = scanner.nextInt();

		switch (option) {
		case 1:
			//Login
			System.out.println();
			login();
			break;
		case 2:
			//Exit
			System.out.println();
			System.out.println("Exit selected. Goodbye!");
			System.exit(0);
			break;
		default:
			//TODO: Validate input
			System.out.println();
			System.out.println("Invalid selection");
			System.out.println("Please try again");
			System.out.println();
			unvalidatedMenu();
		}

	}

	public void login() {

		System.out.print("Please enter your login name: ");

		setLoginName(scanner.next());

		System.out.println();

		checkValidSession();
	}


	public void checkValidSession() {
		System.out.println();
		System.out.println("Validating login name......... ");
		System.out.println();
		if(!bankService.bankHasLoginName(getLoginName())) {
			System.out.println("Not a valid login name or password; please try again");
			System.out.println();
			login();
		}
		else {
			validatedMenu();
		}

	}

	//Present Data to a validated user
	public void validatedMenu() {

		String logNme = getLoginName();

		System.out.println("============================");
		System.out.println("    Welcome " + logNme + "! ");
		System.out.println("============================");
		System.out.println("|                          |");
		System.out.println("| 1. View Combined Balance |");
		System.out.println("| 2. View Itemized Balance |");
		System.out.println("| 3. Withdraw Money        |");
		System.out.println("| 4. Deposit Money         |");
		System.out.println("| 5. Logout                |");
		System.out.println("|                          |");
		System.out.println("============================");

		//LOGGER.info(bankService.getAccountBalance(getLoginId()));

		System.out.println();
		System.out.print("Select option: ");

		int option = scanner.nextInt();


		switch (option) {
		case 1:
			//View Balance			
			double accountBalance = bankService.getCombinedAccountBalance(logNme);
			
			System.out.println();
			System.out.println("|============================================|");
			System.out.println("  Your combined account balance is: " + accountBalance);
			System.out.println("|============================================|");
			System.out.println();
			System.out.println("Please select another menu choice");
			System.out.println();
			
			//Back to validatedMenu
			validatedMenu();
			break;
		case 2:
			//Get an itemized balance (by account)
			//> Get a list of accounts
			long accountNumber = getAccountNumber();
			System.out.println("You Chose Acct#: " + accountNumber);
			
			//>> Display balance of selected account
			accountBalance = bankService.getSingleAccountBalance(logNme, accountNumber);			
			System.out.println("The balance on that account is: " + accountBalance);
			
			// Back to validatedMenu
			validatedMenu();
			break;
		
		case 3: 
			//Withdraw Money
			//> Get a list of accounts
			//>> Choose which account to withdraw from
			accountNumber = getAccountNumber();
			System.out.println("You Chose Acct#: " + accountNumber);
			
			//>> Display balance of selected account
			accountBalance = bankService.getSingleAccountBalance(logNme, accountNumber);			
			System.out.println("The balance on that account is: " + accountBalance);
			
			System.out.println();
			System.out.print("Enter the amount to withdraw (in (D.CC) format): ");
			double withdrawAmt = scanner.nextDouble();
			
			//The highly scientific withdrawal math:
			accountBalance -= withdrawAmt;
			//>>>Display updated balance of selected account
			System.out.println("The new balance on that account is: " + accountBalance);
			
			//>>>Send the updated amount back to the database for persistence
			bankService.setNewAccountBalance(accountBalance, accountNumber);
			
			// Back to validatedMenu
			validatedMenu();
			break;
		
		case 4:
			//Deposit Money
			//> Get a list of accounts
			//> Choose which account to deposit to
		
			accountNumber = getAccountNumber();
			System.out.println("You Chose Acct#: " + accountNumber);
			
			//>> Display balance of selected account
			accountBalance = bankService.getSingleAccountBalance(logNme, accountNumber);			
			System.out.println("The balance on that account is: " + accountBalance);
			
			System.out.println();
			System.out.print("Enter the amount to deposit (in (D.CC) format): ");
			double depositAmt = scanner.nextDouble();
			
			//The highly scientific deposit math:
			accountBalance += depositAmt;
			//>>>Display updated balance of selected account
			System.out.println("The new balance on that account is: " + accountBalance);
			
			//>>>Send the updated amount back to the database for persistence
			bankService.setNewAccountBalance(accountBalance, accountNumber);
			
			//Back to validatedMenu
			validatedMenu();
			break;
		case 5:
			System.out.println();
			System.out.println("Logout selected.......");
			System.out.println("Goodbye!");
			unvalidatedMenu();
			
			break;
		default:
			System.out.println();
			System.out.println("Invalid selection");
			System.out.println("Please try again");
			System.out.println();
			validatedMenu();
		}

	}
	
	
	/**
	 * Helper function for retrieving an accountNumber
	 * 
	 * @return accountNumber
	 */
	
	//TODO: Create an implementation of this that allows 
	//each customer to have more than one account type!
	public Long getAccountNumber() {


		Map<String, String> accountHolder = new LinkedHashMap<>();


		System.out.println();
		System.out.println("Your available accounts are: ");
		System.out.println();

		Set<Long> accNum = bankService.getAllAccountNumbers(getLoginName());
		//LOGGER.info(accNum);
		Set<String> accType = bankService.getAllAccountTypes(getLoginName());	
		//LOGGER.info(accType);

		Iterator<Long> it1 = accNum.iterator();
		Iterator<String> it2 = accType.iterator();
		

		int numOfAccounts = 0;
		while(it1.hasNext() && it2.hasNext()) {
			
			String str_accNum = it1.next().toString();
			String str_accType = it2.next().toString();
			accountHolder.put(str_accNum, str_accType);
					
			numOfAccounts++;
			
			System.out.println("Acct #: " + str_accNum + " -- " + str_accType);
		}
		
		System.out.println();
		System.out.print("Please select one of the " + numOfAccounts + " options above: ");
		
		String option = scanner.next();
		
		if(accountHolder.containsKey(option)){
			return Long.parseLong(option);
		}
			
		scanner.close();
		return 0L;
	}



}



