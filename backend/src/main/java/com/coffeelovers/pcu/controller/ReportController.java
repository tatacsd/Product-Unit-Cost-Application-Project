package com.coffeelovers.pcu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffeelovers.pcu.model.Product;
import com.coffeelovers.pcu.model.ProductRepository;
import com.coffeelovers.pcu.model.SupplierRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ReportController {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	SupplierRepository supplierRepository;
	
	/**
	 * this method retern the filal report
	 * @return
	 */
	@GetMapping("/report")
	public ResponseEntity<Object> getAllProduct(){
		List<Object> report = new ArrayList<>();
		
		try {
			report.add(productRepository.findAll().stream().count()); // number of product
			// most expensive product
			// most cheapest product
			
			// most expensive product in term of raw materials
			// most expensive product in term of variable cost

			report.add(supplierRepository.findAll().stream().count()); // number of suppliers
			
			return new ResponseEntity<>(report, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
