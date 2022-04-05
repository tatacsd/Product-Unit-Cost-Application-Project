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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/***
 * this class  represents the factories that use the system
 */
@Entity
@Table(name = "raw_material")
public class RawMaterial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	
	@Column(name = "name")
	private String name;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "product_raw_material", joinColumns = {
	@JoinColumn(name = "raw_material_id", referencedColumnName = "Id") }, inverseJoinColumns = {
			@JoinColumn(name = "productID", referencedColumnName = "productID") })
	private Set<Product> product = new HashSet<>();
		
	/***
	 * 
	 * @param name
	 */
	public RawMaterial( String name) {
		this.name = name;
	}
	
	public RawMaterial() {

	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}


	
	

}