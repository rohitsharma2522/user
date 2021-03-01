package com.nagarro.user.entities;

public class Users {
	String userId;
	String userName;
	Address address;
	
	public Users(String userId, String userName, Address address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.address = address;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
