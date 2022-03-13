package com.coffeelovers.pcu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffeelovers.pcu.model.FixedCost;
import com.coffeelovers.pcu.model.FixedCostRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FixCostsController {
	
	@Autowired
	FixedCostRepository fixedCostRepository;

	@GetMapping("/fixedCosts")
	public ResponseEntity<List<FixedCost>> getAllFixedCosts(){	
		try {			
			List<FixedCost> fixedCostsArray = new ArrayList<FixedCost>();	
			fixedCostRepository.findAll().forEach(fixedCostsArray::add);
			return new ResponseEntity<>(fixedCostsArray,HttpStatus.OK);
			
		}catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@GetMapping("/fixedCosts/{id}")
	public ResponseEntity<FixedCost> getFixedCostsById(@PathVariable("id") long fixedCostsID){
		try {
			Optional<FixedCost> fixedCostsData = fixedCostRepository.findById(fixedCostsID);
			if(fixedCostsData.isPresent())
				return new ResponseEntity<>(fixedCostsData.get(),HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/fixedCosts/{id}")
	public ResponseEntity<FixedCost> updatFixedCosts(@PathVariable("id") long id, @RequestBody FixedCost fixedCosts){
		try {
			Optional<FixedCost> fixedCostsData = fixedCostRepository.findById(id);
			if(fixedCostsData.isPresent()) {
				FixedCost _fixedCosts = fixedCostsData.get();
				_fixedCosts.setDateTime(fixedCosts.getDateTime());
				_fixedCosts.setDescription(fixedCosts.getDescription());
				_fixedCosts.setFixedCostID(fixedCosts.getFixedCostID());
				_fixedCosts.setValue(fixedCosts.getValue());			
				return new ResponseEntity<>(fixedCostRepository.save(_fixedCosts), HttpStatus.OK);				
			}else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}	
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/fixedCosts")
	public ResponseEntity<HttpStatus> deleteAllFixedCosts(){
		try {
			fixedCostRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/fixedCosts/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id){
		try {
			fixedCostRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
