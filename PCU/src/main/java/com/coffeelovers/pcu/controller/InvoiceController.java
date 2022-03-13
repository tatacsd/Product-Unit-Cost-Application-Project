package com.coffeelovers.pcu.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffeelovers.pcu.model.Invoice;
import com.coffeelovers.pcu.model.InvoiceRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class InvoiceController {

	@Autowired
	InvoiceRepository invoiceRepository;

	// Get one
	@GetMapping("/invoice/{id}")
	public ResponseEntity<Invoice> getFixedCostsById(@PathVariable("id") long invoiceID) {
		try {
			Optional<Invoice> invoiceData = invoiceRepository.findById(invoiceID);
			if (invoiceData.isPresent())
				return new ResponseEntity<>(invoiceData.get(), HttpStatus.OK);
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
