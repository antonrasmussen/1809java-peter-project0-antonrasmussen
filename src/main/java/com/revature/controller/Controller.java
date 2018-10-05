package com.revature.controller;

import java.util.Scanner;

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
	
	private String loginName = "";
	
	//Figure out how this applies to hash
	private String loginPassword = "";
	
	public Controller() {}	

	public Controller(String loginName, String loginPassword) {
		this.loginName = loginName;
		this.loginPassword = loginPassword;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	

	public void start() {
		Scanner	scanner = new Scanner(System.in);
		System.out.print("Please enter your login name: ");
		setLoginName(scanner.next());
		System.out.println();
		System.out.print("Please enter your password: ");
		setLoginPassword(scanner.next());
		System.out.println();
		scanner.close();
		
		//Mask password display
		//TODO: Mask the actual input
		char[] unhashedPass = getLoginPassword().toCharArray();
		StringBuffer hashedPass = new StringBuffer();
		for(char c: unhashedPass) {
			hashedPass.append("*");
		}
		
		System.out.println("Your login name is: " + getLoginName());
		System.out.println("Your password is: " + hashedPass.toString());
	}

	
	//Get Data from User by calling Service???

	//Present Data to the User

	//Validate user input




}
