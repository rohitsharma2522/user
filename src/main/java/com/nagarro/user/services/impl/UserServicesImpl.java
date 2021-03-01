package com.nagarro.user.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.nagarro.user.entities.Address;
import com.nagarro.user.entities.ProviderDetailsWithBooking;
import com.nagarro.user.entities.Users;
import com.nagarro.user.services.UserServices;


@Service
public class UserServicesImpl implements UserServices
{
	List<Users> users= new ArrayList<>();

	@Override
	public List<Users> getAllUsers() {
		users.add(new Users("1", "Rohit", new Address("2684-B", "Punjab", "Mohali",160062)));
		users.add(new Users("2", "Vibhav", new Address("2684-B", "Punjab", "Mohali",160061)));
		users.add(new Users("3", "Raj", new Address("2684-B", "Punjab", "Mohali",160071)));
		users.add(new Users("4", "Sobish", new Address("2684-B", "Punjab", "Mohali",160071)));
		return users;
	}

	@Override
	public Users getUserById(String userId) {
		Optional<Users> result = getAllUsers().stream().filter(p -> userId.equals(p.getUserId()))
				.findFirst();
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}
	//Service-Provider is sending details
	@Override
	@JmsListener(destination="SendProviderDetailsToUser")
	public void providerDetails(ProviderDetailsWithBooking providerDetails) {
		System.out.println("Provider details are "+ providerDetails);
	}

}
