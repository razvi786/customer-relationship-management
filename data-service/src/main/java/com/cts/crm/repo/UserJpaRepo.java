package com.cts.crm.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.crm.model.User;

public interface UserJpaRepo extends JpaRepository<User,Integer>{
	
//	@Query("FROM User user WHERE email=?1 AND password=?2")
	public Optional<User> findByEmailAndPassword(String email,String password);

}
