package com.revature.model;

import java.io.Serializable;

public class Customer implements Comparable<Customer>, Serializable {

	/**
	 * Java 1.x Compatibility
	 */
	private static final long serialVersionUID = 3512802514361290060L;

	/**
	 * C_ID (PK) - Must be unique + not nullable
	 */
	private Long id;

	/**
	 * C_FIRST_NAME - Not nullable
	 */
	private String firstName;

	/**
	 * C_LAST_NAME - Not nullable
	 */
	private String lastName;

	/**
	 * C_EMAIL_ADDRESS - Can be null
	 */
	private String emailAddress;
	
	/**
	 * C_LOGIN_NAME - Not nullable
	 */
	private String loginName;


	//	/**
	//	 * A_ACCOUNT_NUMBER (FK) - References A_ACCOUNT_NUMBER, nullable (e.g. customer has no accounts set up)
	//	 */
	//	private Long accountNumber;

	/**
	 * Account
	 */
	private Account account;

	public Customer() {}


	public Customer(Long id, String firstName, String lastName, String emailAddress, String loginName,
			Account account) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.loginName = loginName;
		this.account = account;
	}
	
	public Customer(Long id) {
		this.id = id;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
		return result;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailAddress="
				+ emailAddress + ", loginName=" + loginName + ", account=" + account + "]";
	}

	@Override
	public int compareTo(Customer customer) {

		return new Long(this.id).compareTo(customer.id);
	} 



}
