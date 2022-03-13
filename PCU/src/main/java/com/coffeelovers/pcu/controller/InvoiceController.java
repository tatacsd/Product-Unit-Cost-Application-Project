package com.coffeelovers.pcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffeelovers.pcu.model.InvoiceRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class InvoiceController {

	@Autowired
	InvoiceRepository invoiceRepository;

	// @GetMapping("/fixedCosts")
	// public ResponseEntity<List<FixedCost>> getAllFixedCosts(){	
	// 	try {			
	// 		List<FixedCost> fixedCostsArray = new ArrayList<FixedCost>();	
	// 		fixedCostRepository.findAll().forEach(fixedCostsArray::add);
	// 		return new ResponseEntity<>(fixedCostsArray,HttpStatus.OK);

	// 	}catch (Exception e) {
	// 		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	// 	}		
	// }

	// Get one
	@GetMapping("/invoiceController/{id}")
	public ResponseEntity<InvoiceRepository> getFixedCostsById(@PathVariable("id") long fixedCostsID) {
		try {
			Optional<InvoiceRepository> fixedCostsData = invoiceRepository.findById(fixedCostsID);
			if (fixedCostsData.isPresent())
				return new ResponseEntity<>(fixedCostsData.get(), HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get all

	// put id

	// post 

	// delete one

}
