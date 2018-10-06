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
	public boolean insert(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertProceedure(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertProceedure(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Account> findAllAccounts() {
		try(Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ACCOUNT A, CUSTOMER C WHERE A.C_ID = C.C_ID";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			//The Band Set
			Set<Account> accounts = new HashSet<>();
			while(result.next()) {
				accounts.add(new Account(
						result.getLong("A_ACCOUNT_NUMBER"),
						result.getString("A_ACCOUNT_TYPE"),
						result.getString("A_ACCOUNT_STATUS"),
						result.getDouble("A_ACCOUNT_BALANCE"),
						new Customer(
								result.getLong("C_ID")					
								),
						result.getString("ACCOUNT_HASH")
						));
			}
			
			if(accounts.size() == 0) {
				return null;
			}
			
			return accounts;
		} catch (SQLException e) {
			
			LOGGER.error("Couldn't retrieve all accounts", e);
		}
		return null;
	}

	@Override
	public Set<Customer> findAllCustomers() {

		return null;
	}

	@Override
	public Account findByAccountNumber(long accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findByCustomerId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Account> findByAccountType(String accountType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Customer> findByCustomerName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		LOGGER.info(new BankRepositoryJdbc().findAllAccounts());
	}

}
