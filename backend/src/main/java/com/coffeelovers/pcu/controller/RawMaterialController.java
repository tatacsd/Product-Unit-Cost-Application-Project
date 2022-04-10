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

import com.coffeelovers.pcu.model.RawMaterial;
import com.coffeelovers.pcu.model.RawMaterialRepository;
import com.coffeelovers.pcu.model.RawMaterial;
import com.coffeelovers.pcu.model.RawMaterialRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RawMaterialController {
	
	@Autowired
	RawMaterialRepository rawMaterialRepository;
	
	@GetMapping("/rawMaterials")
	public ResponseEntity<List<RawMaterial>> getAllRawMaterial(){	
		try {
			List<RawMaterial> rawMaterial = new ArrayList<>();
				rawMaterialRepository.findAll().forEach(rawMaterial::add);
				return new ResponseEntity<>(rawMaterial, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/rawMaterials/{id}")
	public ResponseEntity<RawMaterial> getRawMaterialByID(@PathVariable("id") long rawMaterialID){	
		try {
			Optional<RawMaterial> rawMaterialData = rawMaterialRepository.findById(rawMaterialID);	
			if(rawMaterialData.isPresent()) {
				return new ResponseEntity<>(rawMaterialData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@PutMapping("/rawMaterials/{id}")
	public ResponseEntity<RawMaterial> updateRawMaterial(@PathVariable("id") long id, @RequestBody RawMaterial rawMaterial){
		try {
			Optional<RawMaterial> rawMaterialData = rawMaterialRepository.findById(id);
			if(rawMaterialData.isPresent()) {
				RawMaterial _rawMaterial = rawMaterialData.get();
				_rawMaterial.setId(id);
				
								
				if(rawMaterial.getName() != null) 
					_rawMaterial.setName(rawMaterial.getName());
				

				return new ResponseEntity<>(rawMaterialRepository.save(_rawMaterial), HttpStatus.OK);				
			}else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/rawMaterials")
	public ResponseEntity<HttpStatus> deleteAllRawMaterial(){
		try {
			rawMaterialRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/rawMaterials/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		try {
			rawMaterialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/rawMaterials")
	public ResponseEntity<RawMaterial> createRawMaterial(@RequestBody RawMaterial rawMaterial){
		try {
			return new ResponseEntity<>(rawMaterialRepository.save(rawMaterial), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
