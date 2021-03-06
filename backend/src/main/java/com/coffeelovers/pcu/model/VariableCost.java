package com.coffeelovers.pcu.model;

import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/***
 * this class  represents the actors (factories) variables costs (water, eletricity, gas, etc.)
 */
@Entity
@Table(name = "variable_cost")
public class VariableCost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long variableCostID;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "value")
	private double value;
	
	@Column(name = "date")
	private LocalDate dateTime;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "product_variable_cost", joinColumns = {
	@JoinColumn(name = "variableCostID", referencedColumnName = "variableCostID") }, inverseJoinColumns = {
			@JoinColumn(name = "productID", referencedColumnName = "productID") })
	private Set<Product> product = new HashSet<>();
		

	public VariableCost() {}

	/***
	 * 
	 * @param description
	 * @param value
	 * @param dateTime
	 */
	public VariableCost(String description, double value, String dateTime) {
		this.description = description;
		this.value = value;
		this.dateTime = LocalDate.parse(dateTime);
	}
	
	/***
	 * 
	 * @param description
	 * @param value
	 */
	public VariableCost(String description, double value) {
		this.description = description;
		this.value = value;
		this.dateTime = LocalDate.now();
	}

	public long getVariableCostId() {
		return variableCostID;
	}

	public void setVariableCostId(long variableCostId) {
		this.variableCostID = variableCostId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public LocalDate getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = LocalDate.parse(dateTime);
	}

	public void setDateTime(LocalDate dateTime) {
		this.dateTime = dateTime;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}




	
	
	
}