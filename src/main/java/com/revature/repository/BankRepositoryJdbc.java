package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.model.Account;
import com.revature.model.Customer;
import com.revature.util.ConnectionUtil;

public class BankRepositoryJdbc implements BankRepository {

	private static Logger LOGGER = Logger.getLogger(ConnectionUtil.class);


	// Account methods

	@Override
	public boolean insert(Account account) {
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;

			String sql = "INSERT INTO ACCOUNT VALUES (NULL, ?, ?, ?, ?, NULL)";

			PreparedStatement statement = connection.prepareStatement(sql);
			//-->account number is NULL
			statement.setString(++parameterIndex, account.getAccountType());
			statement.setString(++parameterIndex, account.getAccountStatus());
			statement.setDouble(++parameterIndex, account.getAccountBalance());
			statement.setLong(++parameterIndex, account.getCustomer().getId()); // Chain to get PK
			//-->account hash is NULL

			//returns int num of rows
			if(statement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {

			LOGGER.error("Couldn't insert account", e);
		}

		return false;
	}


	@Override
	public Set<Account> findAllAccounts() {
		try(Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ACCOUNT";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			//The Account Set
			Set<Account> accounts = new HashSet<>();
			while(result.next()) {
				accounts.add(new Account(
						result.getLong("A_ACCOUNT_NUMBER"),
						result.getString("A_ACCOUNT_TYPE"),
						result.getString("A_ACCOUNT_STATUS"),
						result.getDouble("A_ACCOUNT_BALANCE"),
						new Customer(
								result.getLong("C_ID")),
						result.getString("ACCOUNT_HASH")
						));
			}

			if(accounts.size() == 0) {
				
				LOGGER.info("No accounts to display");
				return null;
			}

			return accounts;
		} catch (SQLException e) {

			LOGGER.error("Couldn't retrieve all accounts", e);
		}
		return null;
	}

	@Override
	public Account findByAccountNumber(long accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Account> findByAccountType(String accountType) {
		// TODO Auto-generated method stub
		return null;
	}

	//Customer methods

	@Override
	public boolean insert(Customer customer) {
		LOGGER.info("In bank repository");
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;

			String sql = "INSERT INTO CUSTOMER VALUES (NULL, ?, ?, ?, ?, NULL)";

			PreparedStatement statement = connection.prepareStatement(sql);
			//-->customer id is NULL
			statement.setString(++parameterIndex, customer.getFirstName());
			statement.setString(++parameterIndex, customer.getLastName());
			statement.setString(++parameterIndex, customer.getLoginName());
			statement.setString(++parameterIndex, customer.getEmailAddress());
			 // Chain to get PK
			//-->customer hash is NULL

			//returns int num of rows
			if(statement.executeUpdate() > 0) {
				LOGGER.info("statement.executeUpdate > 0");
				return true;
			}
		} catch (SQLException e) {

			LOGGER.error("Couldn't insert customer", e);
		}

		return false;
	}


	@Override
	public Set<Customer> findAllCustomers() {
		try(Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM CUSTOMER";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			//The Customer Set
			Set<Customer> customers = new HashSet<>();
			while(result.next()) {
				customers.add(new Customer(
						result.getString("C_FIRST_NAME"),
						result.getString("C_LAST_NAME"),
						result.getString("C_LOGIN_NAME"),
						result.getString("C_EMAIL_ADDRESS")));
			}

			if(customers.size() == 0) {
				
				LOGGER.info("No customers to display");
				return null;
			}

			return customers;
		} catch (SQLException e) {

			LOGGER.error("Couldn't retrieve all customers", e);
		}
		return null;
	}

	
	//View balance (by customer)
	//> SELECT SUM (A_ACCOUNT_BALANCE) FROM ACCOUNT WHERE C_ID = [C_ID];

	@Override
	public double findBalanceByCustomerId(long id) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT A_ACCOUNT_BALANCE FROM ACCOUNT WHERE C_ID = " + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			//The Customer Set
			double balanceAccumulator = 0;
			while(result.next()) {
				//No need to do SELECT SUM(A_ACCOUNT_BALANCE) because of below accumulator
				balanceAccumulator += result.getDouble("A_ACCOUNT_BALANCE");
			}
			
			return balanceAccumulator;
		} catch (SQLException e) {

			LOGGER.error("Couldn't retrieve balance", e);
		}
		return 0;
	}
	
	@Override
	public double findBalanceByLoginName(String loginName) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			
			/////////////////////////////////////////////////////////////////////////
			String sql = "select account.c_id, "
					+ "account.a_account_number, "
					+ "account.a_account_balance, "
					+ "customer." + loginName
					+ " from account "
					+ "inner join customer "
					+ "on account.c_id = customer.c_id";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			//The Customer Set
			double balanceAccumulator = 0;
			while(result.next()) {
				//No need to do SELECT SUM(A_ACCOUNT_BALANCE) because of below accumulator
				balanceAccumulator += result.getDouble("A_ACCOUNT_BALANCE");
			}
			
			return balanceAccumulator;
		} catch (SQLException e) {

			LOGGER.error("Couldn't retrieve balance", e);
		}
		return 0;
	}


	@Override
	public Set<Customer> findByName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Set<Customer> findByLoginName() {
		try(Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT C_LOGIN_NAME FROM CUSTOMER";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			//The Customer Set
			Set<Customer> customer = new HashSet<>();
			while(result.next()) {
				customer.add(new Customer(
						result.getString("C_LOGIN_NAME")));
			}
			
			if(customer.size() == 0) {
				
				LOGGER.info("NOT a valid customer size");
				return null;
			}


			return customer;
		} catch (SQLException e) {

			LOGGER.error("Couldn't retrieve login names", e);
		}
		return null;
	}
	
	@Override
	public boolean isValidLoginName(String loginName) {
		LOGGER.info("In isValidLoginName");
		boolean truthFlag = false;
		Set<Customer> customer = new BankRepositoryJdbc().findByLoginName();
		for(Customer cust: customer) {
			
			if(cust.getLoginName().equals(loginName)) {
				LOGGER.info("VALID");
				return true;
			}
			else {
				LOGGER.info("INVALID");
				truthFlag = false;
			}
		}

		return truthFlag;
	}
	

	//View balance (by account): 
	//> SELECT A_ACCOUNT_BALANCE FROM ACCOUNT WHERE A_ACCOUNT_NUMBER = [A_ACCOUNT_NUMBER];
	
	//Deposit money: 
	//> UPDATE ACCOUNT SET A_ACCOUNT_BALANCE = [Money to Deposit] where C_ID = [C_ID] and A_ACCOUNT_NUMBER = [A_ACCOUNT_NUMBER];

	public static void main(String[] args) {
		//LOGGER.info(new BankRepositoryJdbc().findAllAccounts());
		//LOGGER.info(new BankRepositoryJdbc().findAllCustomers());
		//LOGGER.info(new BankRepositoryJdbc().findByLoginName());
		LOGGER.info(new BankRepositoryJdbc().findBalanceByCustomerId(1L));


		
	}

}
