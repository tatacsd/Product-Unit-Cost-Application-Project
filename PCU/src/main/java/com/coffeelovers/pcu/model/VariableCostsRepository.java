package com.coffeelovers.pcu.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VariableCostsRepository extends JpaRepository<VariableCosts, Long> {

	List<VariableCosts> findByDiscription(String discription);
	
	List<VariableCosts> findByValue(int value);
	
	List<VariableCosts> findByDate(String dateAndTime);
	
	
	
}
