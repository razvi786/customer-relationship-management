package com.cts.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.crm.config.DataServiceConfig;
import com.cts.crm.model.User;
import com.cts.crm.repo.UserJdbcRepo;
import com.cts.crm.repo.UserJpaRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
	
	@Autowired
	DataServiceConfig properties;
	
	@Autowired
	UserJpaRepo userJpaRepo;
	
	@Autowired
	UserJdbcRepo userJdbcRepo;

	@Override
	public User createUser(User user) {
		log.info("Inside Create User [User Service]");
		return userJpaRepo.save(user);
//		if(properties.getJpaEnable().equalsIgnoreCase("Y"))
//			return userJpaRepo.save(user);
//		else
//			return userJdbcRepo.save(user);
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		log.info("Inside Get User By Email and Password [User Service]");
		return userJpaRepo.findByEmailAndPassword(email, password).orElse(null);
//		if(properties.getJpaEnable().equalsIgnoreCase("Y"))
//			return userJpaRepo.findByEmailAndPassword(email, password).orElse(null);
//		else
//			return userJdbcRepo.findByEmailAndPassword(email, password).orElse(null);		
	}

}
