package com.feldmanm.app.ws.userservice.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feldmanm.app.ws.shared.Utils;
import com.feldmanm.app.ws.ui.model.request.UserDetailsRequestModel;
import com.feldmanm.app.ws.ui.model.respose.UserRest;
import com.feldmanm.app.ws.userservice.UserSevice;

@Service
public class UserServiceImpl implements UserSevice{

	Map<String, UserRest> users;
	
	Utils utils;
	
	public UserServiceImpl() {
		
	}
	
	//Constructor based dependency Injection
	@Autowired
	public UserServiceImpl(Utils utils) {
		this.utils = utils;
	}
	
	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getFirstName());
		
		String userId = utils.generateUserId();
		returnValue.setUserId(userId);
		
		if (users==null) users = new HashMap<String, UserRest>();
		users.put(userId, returnValue);
		
		return returnValue;
	}

}
