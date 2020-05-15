package com.cts.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.crm.config.TransactionUtil;
import com.cts.crm.exception.UserNotFoundException;
import com.cts.crm.model.User;
import com.cts.crm.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TransactionUtil transactionUtil;
	
	@PostMapping("users")
	public ResponseEntity<User> createUser(@RequestBody User user,@RequestHeader HttpHeaders headers) {
		transactionUtil.setTransactionId(headers);
		log.info("Inside Create User [User Controller]");
		User createdUser = userService.createUser(user);
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
	}
	
	@GetMapping("users/{email}/{password}")
	public ResponseEntity<User> getUserByEmailAndPassword(@PathVariable String email, @PathVariable String password,@RequestHeader HttpHeaders headers) {
		transactionUtil.setTransactionId(headers);
		log.info("Inside Get User By Email and Password [User Controller]");
		User user = userService.getUserByEmailAndPassword(email, password);
		if(user==null)
			throw new UserNotFoundException("No user found with given Username and Password");
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
}
