package com.cts.crm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.crm.model.Customer;

public interface CustomerJpaRepo extends JpaRepository<Customer, Integer>{

}
