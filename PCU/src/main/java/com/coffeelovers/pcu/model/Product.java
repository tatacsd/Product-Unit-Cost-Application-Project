package com.coffeelovers.pcu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productID;
	
	@Column(name= "materialsID")
	private long MaterialsID;
	
	@Column(name= "variableCosts")
	private long variableCosts;
	
	@Column(name= "pic")
	private long pic;
	
	@Column(name= "size")
	private long size;
	public Product() {
		
	}
	
	// Generate Setters and getters
	
}
