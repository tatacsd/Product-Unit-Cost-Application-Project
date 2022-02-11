package com.coffeelovers.pcu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "variableCosts")
public class VariableCosts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long variableCostId;
	
	@Column(name = "description")
	private String description;
	
	@Column(name="value")
	private double value;
	
	@Column(name="dateTime")
	private String dateTime;
	
	public VariableCosts() {}

	public VariableCosts(String description, double value, String dateTime) {
		this.description = description;
		this.value = value;
		this.dateTime = dateTime;
	}

	public long getVariableCostId() {
		return variableCostId;
	}

	public void setVariableCostId(long variableCostId) {
		this.variableCostId = variableCostId;
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

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
}
