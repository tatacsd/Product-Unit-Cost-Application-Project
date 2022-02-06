package com.coffeelovers.pcu.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	// Add the abstract methods
	List<Product> findByMaterialsID(Long materialID);
	List<Product> findByvariableCosts(Long variableCosts);
	List<Product> findBypic(Long pic);
	List<Product> findBysize(String size);
}
