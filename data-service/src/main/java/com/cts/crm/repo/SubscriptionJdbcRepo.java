package com.cts.crm.repo;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cts.crm.config.DataServiceConfig;
import com.cts.crm.model.Subscription;

@Repository
public class SubscriptionJdbcRepo {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DataServiceConfig properties;
	
	@Autowired
	CustomerJdbcRepo customerJdbcRepo;
	
	public Optional<Subscription> findById(Integer id){
		Subscription subscription=jdbcTemplate.queryForObject(properties.getSubscriptionFindById(), new Object[] {id}, new BeanPropertyRowMapper<>(Subscription.class));
		return Optional.ofNullable(subscription);
	}
	
	public Subscription save(Subscription subscription) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc=(con)->{
			PreparedStatement ps=con.prepareStatement(properties.getSubscriptionSave(),Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, subscription.getId());
			ps.setBoolean(2, subscription.isActive());
			ps.setDate(3, subscription.getExpiryDate());
			ps.setString(4, subscription.getName());
			ps.setInt(5, subscription.getCustomerId().getCustomerId());
			return ps;
		};
		jdbcTemplate.update(psc, keyHolder);
		return findById(keyHolder.getKey().intValue()).orElse(null);
	}
	
	public List<Subscription> findAll(){
		return jdbcTemplate.query(properties.getSubscriptionFindAll(), new BeanPropertyRowMapper<Subscription>(Subscription.class));
	}
	
	public List<Subscription> viewActiveSubscriptions(int customerId){
		return jdbcTemplate.queryForList(properties.getSubscriptionViewActiveSubscriptions(),
				new Object[] {customerId}, Subscription.class);
	}
	
	public void batchInactiveSubscription(List<Subscription> subscriptions) {
//		https://mkyong.com/spring/spring-jdbctemplate-batchupdate-example/
//		https://docs.spring.io/spring/docs/4.0.x/spring-framework-reference/html/jdbc.html
		int batchSize=100;
		ParameterizedPreparedStatementSetter<Subscription> parameterizedPreparedStatementSetter = (ps,sub) -> {ps.setInt(1, sub.getId());};
		jdbcTemplate.batchUpdate(properties.getSubscriptionBatchInactiveSubscription(), subscriptions, batchSize, parameterizedPreparedStatementSetter);
	}

}
