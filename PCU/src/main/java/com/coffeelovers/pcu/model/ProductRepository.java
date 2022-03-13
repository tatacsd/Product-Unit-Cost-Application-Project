package com.coffeelovers.pcu.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByProductID(Long productId);
	List<Product> findByMaterialsID(Long materialId);
	List<Product> findByVariableCosts(Long variableCosts);
	List<Product> findBySize(String size);
}