package com.coffeelovers.pcu.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/***
 * this class  represents the actors (factories) products portfolio
 */
@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productID;
	
	@Column(name= "code")
	private String code;
	
	@Column(name= "discription")
	private String discription;
	
	@Column(name= "picture")
	private String picture;
	
	@Column(name= "size")
	private String size;
	

	//----
	
	@ManyToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<VariableCost> variableCosts = new HashSet<>();
	
	@ManyToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<RawMaterial> rawMaterials = new HashSet<>();
	//---
	
	
	public Product() {}

	/***
	 * 
	 * @param code
	 * @param discription
	 * @param picture
	 * @param size
	 */
	public Product(String code, String discription, String picture, String size) {
		this.code = code;
		this.discription = discription;
		this.picture = picture;
		this.size = size;
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Set<VariableCost> getVariableCosts() {
		return variableCosts;
	}

	public void setVariableCosts(Set<VariableCost> variableCosts) {
		this.variableCosts = variableCosts;
	}
	
	
	public Set<RawMaterial> getRawMaterials() {
		return rawMaterials;
	}

	public void setRawMaterials(Set<RawMaterial> rawMaterials) {
		this.rawMaterials = rawMaterials;
	}

	public void addVaraibleCost(VariableCost variableCost) {
		this.variableCosts.add(variableCost);
		variableCost.getProduct().add(this);
	}
	
	public void addRawMaterials(RawMaterial rawMaterial) {
		this.rawMaterials.add(rawMaterial);
		rawMaterial.getProduct().add(this);
	}

	
	
	
	
	

	

	

	
	
	
}