package com.cts.crm.repo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cts.crm.config.DataServiceConfig;
import com.cts.crm.model.Customer;

@Repository
public class CustomerJdbcRepo {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DataServiceConfig properties;
	
	public Optional<Customer> findById(Integer id){
		Customer customer=jdbcTemplate.queryForObject(properties.getCustomerFindById(), new Object[] {id}, new BeanPropertyRowMapper<>(Customer.class));
		return Optional.ofNullable(customer);
	}
	
	public Customer save(Customer customer) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc=(con)->{
			PreparedStatement ps=con.prepareStatement(properties.getUserSave(),Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, customer.getCustomerId());
			ps.setString(2, customer.getCircle());
			ps.setString(3, customer.getDp());
			ps.setString(4, customer.getEmail());
			ps.setLong(5, customer.getMobileNumber());
			ps.setString(6, customer.getName());
			return ps;
		};
		jdbcTemplate.update(psc, keyHolder);
		return findById(keyHolder.getKey().intValue()).orElse(null);
	}

}
