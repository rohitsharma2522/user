package com.nagarro.user.services;

import java.util.List;

import com.nagarro.user.entities.ProviderDetailsWithBooking;
import com.nagarro.user.entities.Users;

public interface UserServices {
	List<Users> getAllUsers();
	Users getUserById(String userId);
	public void providerDetails(ProviderDetailsWithBooking providerDetails);
}
