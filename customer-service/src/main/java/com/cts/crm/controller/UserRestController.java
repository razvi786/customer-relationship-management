package com.cts.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.cts.crm.exception.ServerDownException;
import com.cts.crm.model.User;
import com.cts.crm.service.rest.DataServiceRestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@CrossOrigin(origins="*")
public class UserRestController {
	
	@Autowired 
	DataServiceRestTemplate dataServiceRestTemplate;
	
	@PostMapping("users")
	@HystrixCommand(fallbackMethod = "postFallbackUser",ignoreExceptions = HttpClientErrorException.class)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User createdUser = dataServiceRestTemplate.createUser(user).getBody();
		return new ResponseEntity<User>(createdUser,HttpStatus.CREATED);
	}
	
	@GetMapping("users/{email}/{password}")
	@HystrixCommand(fallbackMethod = "getFallbackUser",ignoreExceptions = HttpClientErrorException.class)
	public ResponseEntity<User> getUserByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
		User user = dataServiceRestTemplate.getUserByEmailAndPassword(email, password).getBody();
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	//	Hystrix Fallback Methods	
	public  ResponseEntity<User> postFallbackUser(@RequestBody User user){
		throw new ServerDownException("Server is currently DOWN! please try again later");
	}
	public  ResponseEntity<User> getFallbackUser(@PathVariable String email, @PathVariable String password){
		throw new ServerDownException("Server is currently DOWN! please try again later");
	}
	
}
