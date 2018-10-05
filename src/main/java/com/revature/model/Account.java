package com.revature.model;

import java.io.Serializable;

public class Account implements Comparable<Account>, Serializable {
	
	/**
	 * Java 1.x Compatibility
	 */
	private static final long serialVersionUID = -3706430178014220784L;

	/*
	 * A_ACCOUNT_NUMBER (PK) - (Must be unique + not nullable)
	 */
	private Long accountNumber;
	
	/*
	 * A_ACCOUNT_TYPE - (Not null) - 'CHECKING', 'SAVINGS', 'CUSTODIAL', 'IRA'
	 */	
	private String accountType;
	
	/*
	 * A_ACCOUNT_STATUS - (Can be null) - 'ACTIVE', 'INACTIVE'
	 */
	private String accountStatus;
	
	/*
	 * A_ACCOUNT_BALANCE - (Not null)
	 */
	private double accountBalance;
	
	
	private Customer customer;
	
	public Account() {}

	public Account(Long accountNumber, String accountType, String accountStatus, double accountBalance, Customer customer) {
		
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.accountStatus = accountStatus;
		this.accountBalance = accountBalance;
		this.customer = customer;
	}


	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(accountBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((accountStatus == null) ? 0 : accountStatus.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Double.doubleToLongBits(accountBalance) != Double.doubleToLongBits(other.accountBalance))
			return false;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (accountStatus == null) {
			if (other.accountStatus != null)
				return false;
		} else if (!accountStatus.equals(other.accountStatus))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountType=" + accountType + ", accountStatus="
				+ accountStatus + ", accountBalance=" + accountBalance + "]";
	}

	@Override
	public int compareTo(Account account) {
		
		return new Long(this.accountNumber).compareTo(account.accountNumber);
	}

}
