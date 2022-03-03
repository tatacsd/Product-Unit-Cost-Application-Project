package com.coffeelovers.pcu.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariableCostsRepository extends JpaRepository<VariableCosts, Long> {
	List<VariableCosts> findByVariableCostId(Long variableCostId);
}