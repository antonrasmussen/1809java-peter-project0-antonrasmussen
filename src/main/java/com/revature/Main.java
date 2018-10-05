package com.revature;

import com.revature.controller.Controller;

/** 
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {

	public static void main(String[] args) {
		
		/*
		Menu
		1. Login
		2. Create New Account
		3. Reset password		
		*/
		
		Controller bankController = new Controller();
		bankController.start();
		

	}
}
