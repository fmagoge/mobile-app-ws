package com.feldmanm.app.ws.ui.controller;

import java.nio.channels.NonReadableChannelException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.feldmanm.app.ws.exceptions.UserServiceException;
import com.feldmanm.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.feldmanm.app.ws.ui.model.request.UserDetailsRequestModel;
import com.feldmanm.app.ws.ui.model.respose.UserRest;
import com.feldmanm.app.ws.userservice.UserSevice;


@RestController
@RequestMapping("/users")//http://localhost:8080/users
public class UserController {

	Map<String, UserRest> users;
	
	@Autowired
	UserSevice userService;
	
	/**
	 * Method to return Users according to RequestParams below
	 * @param page
	 * @param limit
	 * @param sort
	 * @return
	 */
	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="limit", defaultValue="50") int limit,
			@RequestParam(value="sort",defaultValue="desc", required=false) String sort) {
		
		return "Get users was called page = "+ page +" limit = "+limit +" and sort = "+sort;
	}
	
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	@GetMapping(path="/{userId}", 
			produces= {
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
					})
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		
		//if(true) throw new UserServiceException("A user service exception is thrown");
		
		if(users.containsKey(userId))
		{
			return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<UserRest>( HttpStatus.NO_CONTENT);
		}		
	}
	
	
	
	/**
	 * Method to create user
	 * @param userDetails
	 * @return
	 */
	@PostMapping(consumes= {
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
					},produces= {
							MediaType.APPLICATION_XML_VALUE, 
							MediaType.APPLICATION_JSON_VALUE
							})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		
		UserRest returnValue = userService.createUser(userDetails);
			
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}
	
	
	
	/**
	 * Method to update user
	 * @param userId
	 * @param userDetails
	 * @return
	 */
	@PutMapping(path="/{userId}", 
			consumes= {
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE
			},produces= {
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
					})
	public UserRest updateUser(@PathVariable String userId,@Valid @RequestBody UpdateUserDetailsRequestModel userDetails ) {
		
		UserRest storedUserDetails = users.get(userId);
		
		storedUserDetails.setFirstName(userDetails.getFirstName());
		storedUserDetails.setLastName(userDetails.getLastName());
		
		users.put(userId, storedUserDetails);
		
		return storedUserDetails;
	}
	
	
	/**
	 * Method to delete user
	 * @param id
	 * @return
	 */
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		
		users.remove(id);
		
		return ResponseEntity.noContent().build();
	}
}





