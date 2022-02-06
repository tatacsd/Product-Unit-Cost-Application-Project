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
	private long materialsID;
	
	@Column(name= "variableCosts")
	private double variableCosts;
	
	@Column(name= "Picture")
	private long pic;
	
	@Column(name= "size")
	private String size;
	
	public Product() {
		
	}

	// Personalized
	public Product(long materialsID, double variableCosts, long pic, String size) {
		this.materialsID = materialsID;
		this.variableCosts = variableCosts;
		this.pic = pic;
		this.size = size;
	}
	
	
// Generate Setters and getters

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

	public long getPic() {
		return pic;
	}

	public void setPic(long pic) {
		this.pic = pic;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	
}
