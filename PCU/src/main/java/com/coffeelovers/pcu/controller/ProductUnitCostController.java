package com.coffeelovers.pcu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coffeelovers.pcu.model.Product;
import com.coffeelovers.pcu.model.ProductRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController // requests
@RequestMapping("/api")
public class ProductUnitCostController {
	
	// Get a repository reference
	@Autowired
	ProductRepository productRepository;
	
	// End point for products
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProduct(@RequestParam(required=false)Long materialID){
		try {
			// Create a list of Products to return as a response
			List<Product> products = new ArrayList<Product>();
			// if there is no param return all
			if(materialID == null) {
				productRepository.findAll().forEach(products::add);
			} else {
				// Get all products with the material id
				productRepository.findByMaterialsID(materialID).forEach(products::add);
			}
			// return the list of products
			return new ResponseEntity<>(products, HttpStatus.OK);
			
		}catch(Exception e) {
			// Return the error as a response
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
