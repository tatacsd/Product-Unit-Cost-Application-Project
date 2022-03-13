package com.coffeelovers.pcu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffeelovers.pcu.model.*;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class VariableCostsController {
	

	@Autowired
	VariableCostsRepository variableCostsRepository;
	
	@GetMapping("/variableCosts")
	public ResponseEntity<List<VariableCost>> getAllVariableCosts(){
		try {
			List<VariableCost> variableCosts = new ArrayList<VariableCost>();
				variableCostsRepository.findAll().forEach(variableCosts::add);
				return new ResponseEntity<>(variableCosts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/variableCosts")
	public ResponseEntity<HttpStatus> deleteAllSuppliers(){
		try {
			variableCostsRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
