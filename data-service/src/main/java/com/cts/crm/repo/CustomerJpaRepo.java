package com.cts.crm.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.crm.model.Customer;

@Transactional
public interface CustomerJpaRepo extends JpaRepository<Customer, Integer>{

}
