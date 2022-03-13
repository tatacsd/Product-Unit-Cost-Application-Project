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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffeelovers.pcu.model.VariableCost;
import com.coffeelovers.pcu.model.VariableCostsRepository;


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
	
	@GetMapping("/variableCosts/{id}")
	public ResponseEntity<VariableCost> getVariableCostByID(@PathVariable("id") long variableCostID){
		try {
			Optional<VariableCost> variableCostData = variableCostsRepository.findById(variableCostID);
			if(variableCostData.isPresent()) {
				return new ResponseEntity<>(variableCostData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}	
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	@PutMapping("/variableCosts/{id}")
	public ResponseEntity<VariableCost> updateVariableCost(@PathVariable("id") long id, @RequestBody VariableCost variableCost){
		try {
			Optional<VariableCost> variableCostData = variableCostsRepository.findById(id);
			if(variableCostData.isPresent()) {
				VariableCost _variableCost = variableCostData.get();
				_variableCost.setVariableCostId(variableCost.getVariableCostId());
				
				if(variableCost.getDateTime() != null) {
					_variableCost.setDateTime(variableCost.getDateTime());
				}
				
				if(variableCost.getDescription() != null) {
					_variableCost.setDescription(variableCost.getDescription());
				}
				
				if(variableCost.getValue() != 0) {
					_variableCost.setValue(variableCost.getValue());
				}
												
				return new ResponseEntity<>(variableCostsRepository.save(_variableCost), HttpStatus.OK);				
			}else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/variableCosts")
	public ResponseEntity<HttpStatus> deleteAllVariableCosts(){
		try {
			variableCostsRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/variableCosts/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		try {
			variableCostsRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/variableCosts")
	public ResponseEntity<VariableCost> createVariableCost(@RequestBody VariableCost variableCost){
		try {
			return new ResponseEntity<>(variableCostsRepository.save(variableCost), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
