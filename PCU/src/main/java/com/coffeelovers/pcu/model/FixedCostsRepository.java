package com.coffeelovers.pcu.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FixedCostsRepository extends JpaRepository<FixedCosts, Long>{
	List<FixedCosts> findByFixedCostsId(Long fixedCostId);
}
