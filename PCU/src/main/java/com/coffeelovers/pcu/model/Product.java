package com.coffeelovers.pcu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productID;
	
	@Column(name= "MaterialsID")
	private long materialsID;
	
	@Column(name= "VariableCosts")
	private double variableCosts;
	
	@Column(name= "Picture")
	private long picture;
	
	@Column(name= "Size")
	private String size;
	
	public Product() {
		
	}

	public Product(long materialsID, double variableCosts, long picture, String size) {
		this.materialsID = materialsID;
		this.variableCosts = variableCosts;
		this.picture = picture;
		this.size = size;
	}
	

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public long getMaterialsID() {
		return materialsID;
	}

	public void setMaterialsID(long materialsID) {
		this.materialsID = materialsID;
	}

	public double getVariableCosts() {
		return variableCosts;
	}

	public void setVariableCosts(double variableCosts) {
		this.variableCosts = variableCosts;
	}

	public long getPicure() {
		return picture;
	}

	public void setPicture(long picture) {
		this.picture = picture;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
