package com.coffeelovers.pcu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffeelovers.pcu.model.Product;
import com.coffeelovers.pcu.model.ProductRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProductUnitCostController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProduct(){
		try {
			List<Product> products = new ArrayList<Product>();
				productRepository.findAll().forEach(products::add);
				return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductByID(@PathVariable("id") long productID){
		Optional<Product> productData = productRepository.findById(productID);
		
		if(productData.isPresent()) {
			return new ResponseEntity<>(productData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}

}
