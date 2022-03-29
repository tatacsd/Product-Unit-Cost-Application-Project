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

import com.coffeelovers.pcu.model.InvoiceDetails;
import com.coffeelovers.pcu.model.InvoiceDetailsRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RawMaterialController {
	
	@Autowired
	InvoiceDetailsRepository rawMaterialRepository;
	
	@GetMapping("/rawMaterials")
	public ResponseEntity<List<InvoiceDetails>> getAllRawMaterial(){	
		try {
			List<InvoiceDetails> rawMaterial = new ArrayList<>();
				rawMaterialRepository.findAll().forEach(rawMaterial::add);
				return new ResponseEntity<>(rawMaterial, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/rawMaterials/{id}")
	public ResponseEntity<InvoiceDetails> getRawMaterialByID(@PathVariable("id") long rawMaterialID){	
		try {
			Optional<InvoiceDetails> rawMaterialData = rawMaterialRepository.findById(rawMaterialID);	
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
	public ResponseEntity<InvoiceDetails> updateRawMaterial(@PathVariable("id") long id, @RequestBody InvoiceDetails rawMaterial){
		try {
			Optional<InvoiceDetails> rawMaterialData = rawMaterialRepository.findById(id);
			if(rawMaterialData.isPresent()) {
				InvoiceDetails _rawMaterial = rawMaterialData.get();
				_rawMaterial.setRawMaterialID(rawMaterial.getRawMaterialID());
				
								
				if(rawMaterial.getNoteString() != null) 
					_rawMaterial.setNoteString(rawMaterial.getNoteString());
				

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
	public ResponseEntity<InvoiceDetails> createRawMaterial(@RequestBody InvoiceDetails rawMaterial){
		try {
			return new ResponseEntity<>(rawMaterialRepository.save(rawMaterial), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
