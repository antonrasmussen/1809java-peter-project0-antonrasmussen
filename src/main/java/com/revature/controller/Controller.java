package com.revature.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.repository.BankRepository;
import com.revature.repository.BankRepositoryJdbc;
import com.revature.service.BankService;
import com.revature.service.BankServiceImpl;

/**
 * The goal of the Controller class is to interpret inputs from 
 * the user to update the model while the view displays the model. 
 * So both the view and controller have a reference to the model.
 * 
 * The view is only used to display things on request of the
 * controller. And also when the model changes.
 * 
 * In this project, given its console based "view" nature, I have
 * opted to put the view functionality within the Controller class.
 */

public class Controller {

	//TODO: Validate user input by searching against database

	public static final Logger LOGGER = Logger.getLogger(Controller.class);

	private BankServiceImpl bankService = new BankServiceImpl();

	public Customer customer = new Customer();
	public Account account = new Account();

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
		System.out.println("============================");


		System.out.print("Select option: ");

		int option = scanner.nextInt();

		switch (option) {
		case 1:
			//Login
			login();
			break;
		case 2:
			System.out.println("Exit selected. Goodbye!");
			System.exit(0);
			break;
		default:
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
		System.out.println("login name is:: " + getLoginName());

		if(!bankService.bankHasLoginName(getLoginName())) {
			System.out.println("Not a valid login name or password; please try again");
			login();
		}
		else {
			//LOGGER.info("In else of inValidSession");
			validatedMenu();
		}

	}

	//Present Data to a validated user
	public void validatedMenu() {

		String nm = getLoginName();

		System.out.println("============================");
		System.out.println("    Welcome " + nm     + "! ");
		System.out.println("============================");
		System.out.println("|                          |");
		System.out.println("| 1. View Balance          |");
		System.out.println("| 2. Withdraw Money        |");
		System.out.println("| 3. Deposit Money         |");
		System.out.println("| 4. Quit                  |");
		System.out.println("|                          |");
		System.out.println("============================");

		//LOGGER.info(bankService.getAccountBalance(getLoginId()));


		System.out.print("Select option: ");

		int option = scanner.nextInt();


		switch (option) {
		case 1:
			//View Balance
			//LOGGER.info(bankService.getAccountBalanceByLoginName(nm));
			System.out.println();
			System.out.println("|-----------------------|");
			System.out.println("Your account balance is: " + bankService.getAccountBalanceByLoginName(nm));
			System.out.println("|-----------------------|");
			System.out.println();
			System.out.println("Please select another menu choice");
			System.out.println();
			validatedMenu();
			break;
		case 2:
			//Withdraw Money
			//> Get a list of accounts
			getAccountNumber();

			//> Choose which account to deposit to
			//getSingleAccountBalanceByLoginNameAndAccountNumber(nm, accountNumber)
			break;
		case 3:
			//Deposit Money
			//> Get a list of accounts
			//> Choose which account to deposit to
			break;
		case 4:
			System.out.println("Quit selected. Goodbye!");
			System.exit(0);
			break;
		default:
			System.out.println();
			System.out.println("Invalid selection");
			System.out.println("Please try again");
			System.out.println();
			validatedMenu();
		}

		scanner.close();

	}
	
	//TODO: Create an implementation of this that allows 
	//each customer to have more than one account type!
	public Long getAccountNumber() {

		Long accountNumber = 0L;

		Map<String, String> accountHolder = new LinkedHashMap<>();


		System.out.println();
		System.out.println("Your available accounts are: "); //+ bankService.getAllAccountNumbers(getLoginName()));


		Set<Long> accNum = bankService.getAllAccountNumbers(getLoginName());
		LOGGER.info(accNum);
		Set<String> accType = bankService.getAllAccountTypes(getLoginName());	
		LOGGER.info(accType);

		Iterator<Long> it1 = accNum.iterator();
		Iterator<String> it2 = accType.iterator();

		while(it1.hasNext() && it2.hasNext()) {
			accountHolder.put(it1.next().toString(),it2.next().toString());
		}


		System.out.println(accountHolder);



		System.out.print("Please make an account selection: ");
		//int option = scanner.nextInt();



		//scanner.close();
		return accountNumber;
	}



}



