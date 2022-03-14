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

import com.coffeelovers.pcu.model.Actor;
import com.coffeelovers.pcu.model.ActorRepository;




@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ActorController {
	
	@Autowired
	ActorRepository actorRepository;

	@GetMapping("/actors")
	public ResponseEntity<List<Actor>> getAllActors(){	
		try {			
			List<Actor> actorArray = new ArrayList<Actor>();	
			actorRepository.findAll().forEach(actorArray::add);
			return new ResponseEntity<>(actorArray,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@GetMapping("/actors/{id}")
	public ResponseEntity<Actor> getActorById(@PathVariable("id") long actorID){
		try {
			Optional<Actor> actorData = actorRepository.findById(actorID);
			if(actorData.isPresent())
				return new ResponseEntity<>(actorData.get(),HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/actors/{id}")
	public ResponseEntity<Actor> updateActor(@PathVariable("id") long id, @RequestBody Actor actor){
		try {
			Optional<Actor> actorData = actorRepository.findById(id);
			if(actorData.isPresent()) {
				Actor _actor = actorData.get();
				_actor.setActorId(actor.getActorId());
				if(actor.getBrand() != null)
					_actor.setBrand(actor.getBrand());
				
				if(actor.getActivity() != null) 
					_actor.setActivity(actor.getActivity());
					
				if(actor.getPhone() != 0) 
					_actor.setPhone(actor.getPhone());
				
				if(actor.getWebside() != null) 
					_actor.setWebside(actor.getWebside());
					
				if(actor.getEmail() != null) 
					_actor.setEmail(actor.getEmail());
					
				if(actor.getPassword() != null) 
					_actor.setPassword(actor.getPassword());
					
				if(actor.getAddress() != null) 
					_actor.setAddress(actor.getAddress());
										
				return new ResponseEntity<>(actorRepository.save(_actor), HttpStatus.OK);				
			}else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/actors")
	public ResponseEntity<HttpStatus> deleteAllActors(){
		try {
			actorRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/actors/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		try {
			actorRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/actors")
	public ResponseEntity<Actor> createActor(@RequestBody Actor actor){
		try {
			return new ResponseEntity<>(actorRepository.save(actor), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
