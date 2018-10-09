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
	 * C_LOGIN_NAME - Not nullable
	 */
	private String loginName;
	
	/*
	 * 	CUSTOMER_HASH
	 */
	private String customerHash;


	/**
	 * C_EMAIL_ADDRESS - Can be null
	 */
	private String emailAddress;
	

	public Customer() {}

	// With every field
	public Customer(Long id, 
			String firstName, 
			String lastName, 
			String loginName,
			String emailAddress, 
			String customerHash) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.loginName = loginName;
		this.emailAddress = emailAddress;	
		this.customerHash = customerHash;

	}
	
	// For findAllCustomers()
	public Customer(String firstName, String lastName, String loginName, String emailAddress) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.loginName = loginName;
		this.emailAddress = emailAddress;	

	} 
	
	public Customer(String loginName) {
		this.loginName = loginName;
	}
	
	public Customer(Long id) {
		this.id = id;
	}


	public Long getId() {
		return id;
	}
	
	public Long getId(Customer customer) {
		return customer.id;
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
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getCustomerHash() {
		return customerHash;
	}

	public void setCustomerHash(String customerHash) {
		this.customerHash = customerHash;
	}	

	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerHash == null) ? 0 : customerHash.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
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
		Customer other = (Customer) obj;
		if (customerHash == null) {
			if (other.customerHash != null)
				return false;
		} else if (!customerHash.equals(other.customerHash))
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
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", loginName=" + loginName
				+ ", customerHash=" + customerHash + ", emailAddress=" + emailAddress + "]";
	}


	@Override
	public int compareTo(Customer customer) {

		return new Long(this.id).compareTo(customer.id);
	} 



}
