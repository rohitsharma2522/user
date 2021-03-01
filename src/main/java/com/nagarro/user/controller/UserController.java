package com.nagarro.user.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.user.entities.UserService;
import com.nagarro.user.entities.Users;
import com.nagarro.user.services.UserServices;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserServices userServices;
	
	@Resource(name = "restTemp")
    private RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;
    
    @RequestMapping("/dashboard/services")
    public List<UserService> getAllServices(@RequestParam("pincode") Optional<Integer> pincode) throws JsonMappingException, JsonProcessingException {
    	String url = "";
    	if(!pincode.isPresent()) {
    		url = "/admin/getallservices";
    	}else {
    		url = "/admin/services/"+pincode.get();
    	}
    	System.out.println("url is "+ url);
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("admin-service", false);
		System.out.println("instance is" + instance);
		String res = restTemplate.getForObject(instance.getHomePageUrl() + url, String.class);
		ObjectMapper objectMapper = new  ObjectMapper();
		List<UserService> readValue = objectMapper.readValue(res, new TypeReference<List<UserService>>() {});
		return readValue;
    }

    
    @GetMapping(value = "/getAllUsers")
	public List<Users> getAllUsers() {
		return userServices.getAllUsers();
	}
}
