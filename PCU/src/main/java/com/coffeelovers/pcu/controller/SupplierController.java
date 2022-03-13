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

import com.coffeelovers.pcu.model.Supplier;
import com.coffeelovers.pcu.model.SupplierRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SupplierController {
	
	@Autowired
	SupplierRepository supplierRepository;
	
	@GetMapping("/suppliers")
	public ResponseEntity<List<Supplier>> GetAllSupplier(){
		try {
			List<Supplier> suppliers = new ArrayList<>();	
			supplierRepository.findAll().forEach(suppliers::add);
			return new ResponseEntity<>(suppliers,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@GetMapping("/suppliers/{id}")
	public ResponseEntity<Supplier> getSupplierByID(@PathVariable("id") long supplierID){
		try {
			Optional<Supplier> supplierData = supplierRepository.findById(supplierID);
			if(supplierData.isPresent()) {
				return new ResponseEntity<>(supplierData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}	
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	@PutMapping("/suppliers/{id}")
	public ResponseEntity<Supplier> updateSupplier(@PathVariable("id") long id, @RequestBody Supplier supplier){
		try {
			Optional<Supplier> supplierData = supplierRepository.findById(id);
			if(supplierData.isPresent()) {
				Supplier _supplier = supplierData.get();
				_supplier.setSupplierID(supplier.getSupplierID());
				
				if(supplier.getAddress() != null) {
					_supplier.setAddress(supplier.getAddress());
				}
				
				if(supplier.getEmail() != null) {
					_supplier.setEmail(supplier.getEmail());
				}
				
				if(supplier.getFirstName() != null) {
					_supplier.setFirstName(supplier.getFirstName());
				}
				
				if(supplier.getLastName() != null) {
					_supplier.setLastName(supplier.getLastName());
				}
				
				if(supplier.getPhone() != 0) {
					_supplier.setPhone(supplier.getPhone());
				}
				
				return new ResponseEntity<>(supplierRepository.save(_supplier), HttpStatus.OK);				
			}else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/suppliers")
	public ResponseEntity<HttpStatus> deleteAllSuppliers(){
		try {
			supplierRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/suppliers/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		try {
			supplierRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/suppliers")
	public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier){
		try {
			return new ResponseEntity<>(supplierRepository.save(supplier), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
