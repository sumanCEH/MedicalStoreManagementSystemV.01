package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entity.Billing;
@Repository
public interface BillingRepository extends JpaRepository<Billing,Long>{
	
	public Billing findByBillId(long id);

}
