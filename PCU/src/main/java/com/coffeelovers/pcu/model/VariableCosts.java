package com.coffeelovers.pcu.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * this class  represents the actors (factories) variables costs (water, eletricity, gas, etc.)
 */
@Entity
@Table(name = "variable_costs")
public class VariableCosts {
	
	/* Variables ----------------------------------------------------------------------------- */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long variableCostId;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "value")
	private double value;
	
	@Column(name = "date")
	private LocalDate dateTime;
	
	/* Constructors --------------------------------------------------------------------------- */
	public VariableCosts() {}

	public VariableCosts(String description, double value, String dateTime) {
		this.description = description;
		this.value = value;
		this.dateTime = LocalDate.parse(dateTime);
	}
	
	public VariableCosts(String description, double value) {
		this.description = description;
		this.value = value;
		this.dateTime = LocalDate.now();
	}

	/* Getters and Setters -------------------------------------------------------------------- */
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

	public LocalDate getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = LocalDate.parse(dateTime);
	}
}
