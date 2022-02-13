package com.coffeelovers.pcu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffeelovers.pcu.model.*;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProductUnitCostController {

	@Autowired
	ProductRepository productRepository;
	ActorRepository actorRepository;
	SupplierRepository supplierRepository;
	VariableCostsRepository variableCostsRepository;
	
	
	@GetMapping("/suppliers")
	public ResponseEntity<List<Supplier>> GetAllSupplier(){
		
		List<Supplier> suppliers = new ArrayList<>();
		
		try {
			supplierRepository.findAll().forEach(suppliers::add);
			return new ResponseEntity<>(suppliers,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@GetMapping("/suppliers/{id}")
	public ResponseEntity<Supplier> getSupplierByID(@PathVariable("id") long supplierID){
		Optional<Supplier> supplierData = supplierRepository.findById(supplierID);
		
		if(supplierData.isPresent()) {
			return new ResponseEntity<>(supplierData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@GetMapping("/actors")
	public ResponseEntity<List<Actor>> GetAllActors(){
		
		List<Actor> actorArray = new ArrayList<Actor>();
		
		try {
			
			actorRepository.findAll().forEach(actorArray::add);
			return new ResponseEntity<>(actorArray,HttpStatus.OK);
			
		}catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@GetMapping("/actors/{id}")
	public ResponseEntity<Actor> GetActorById(@PathVariable("id") long actorID){
		
		Optional<Actor> actorData = actorRepository.findById(actorID);
		if(actorData.isPresent())
			return new ResponseEntity<>(actorData.get(),HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

				
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProduct(){
		try {
			List<Product> products = new ArrayList<Product>();
				productRepository.findAll().forEach(products::add);
				return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductByID(@PathVariable("id") long productID){
		Optional<Product> productData = productRepository.findById(productID);
		
		if(productData.isPresent()) {
			return new ResponseEntity<>(productData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}

	@GetMapping("/variableCosts")
		public ResponseEntity<List<VariableCosts>> getAllVariableCosts(){
			try {
				List<VariableCosts> variableCosts = new ArrayList<VariableCosts>();
					variableCostsRepository.findAll().forEach(variableCosts::add);
					return new ResponseEntity<>(variableCosts, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}


}
