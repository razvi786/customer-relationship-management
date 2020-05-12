package com.cts.crm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.cts.crm.model.Subscription;

public interface SubscriptionJpaRepo extends JpaRepository<Subscription, Integer> {
	
//	@Query("FROM Subscription sub WHERE sub.customerId.id=?1 AND sub.active=TRUE")
	public List<Subscription> viewActiveSubscriptions(int customerId);
	
	//Unused in this Project
	
	@Transactional
	@Modifying
//	@Query("UPDATE Subscription sub SET sub.active=FALSE WHERE sub.id=?1")
	public void inactiveSubscription(int id);
	
//	@Query("FROM Subscription sub WHERE sub.expiryDate=CURRENT_DATE()")
	public List<Subscription> findAllSubscriptionsOfToday();

}
