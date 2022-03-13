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

import com.coffeelovers.pcu.model.*;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RawMaterialController {
	
	@Autowired
	RawMaterialRepository rawMaterialRepository;
	
	@GetMapping("/rawMaterial")
	public ResponseEntity<List<RawMaterial>> getAllRawMaterial(){
		
		try {
			List<RawMaterial> rawMaterial = new ArrayList<RawMaterial>();
				rawMaterialRepository.findAll().forEach(rawMaterial::add);
				return new ResponseEntity<>(rawMaterial, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/rawMaterial/{id}")
	public ResponseEntity<RawMaterial> getRawMaterialByID(@PathVariable("id") long rawMaterialID){
		
		Optional<RawMaterial> rawMaterialData = rawMaterialRepository.findById(rawMaterialID);		
		if(rawMaterialData.isPresent()) {
			return new ResponseEntity<>(rawMaterialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	

}
