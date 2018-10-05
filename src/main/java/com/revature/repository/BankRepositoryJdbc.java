package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
			
			//TODO: add query stuff here V
			String sql = "----------------------";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(++parameterIndex, account.getAccountType());
			statement.setString(++parameterIndex, account.getAccountStatus());
			statement.setDouble(++parameterIndex, account.getAccountBalance());
			statement.setLong(++parameterIndex, account.getCustomer().getId()); // Chain to get PK
			
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Customer> findAllCustomers() {
		// TODO Auto-generated method stub
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

}
