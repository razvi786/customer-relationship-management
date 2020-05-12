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
import com.cts.crm.model.User;

@Repository
public class UserJdbcRepo {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DataServiceConfig properties;
	
	public Optional<User> findById(Integer id){
		User user=jdbcTemplate.queryForObject(properties.getUserFindById(), new Object[] {id}, new BeanPropertyRowMapper<>(User.class));
		return Optional.ofNullable(user);
	}
	
	public Optional<User> findByEmailAndPassword(String email,String password){
		User user = jdbcTemplate.queryForObject(properties.getUserFindByEmailAndPassword(), new Object[] {email,password}, new BeanPropertyRowMapper<>(User.class) );
		return Optional.ofNullable(user);
	}
	
	public User save(User user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc=(con)->{
			PreparedStatement ps=con.prepareStatement(properties.getUserSave(),Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getName());
			ps.setString(4, user.getPassword());
			ps.setString(5, "ROLE_USER");
			return ps;
		};
		jdbcTemplate.update(psc, keyHolder);
		return findById(keyHolder.getKey().intValue()).orElse(null);
	}
}
