package com.coffeelovers.pcu.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * this class  represents the actors (factories) fixed costs (rent, etc.)
 */
@Entity
@Table(name = "fixed_costs")
public class FixedCosts {

	/* Variables ----------------------------------------------------------------------------- */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long fixedCost;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "value")
	private double value;
	
	@Column(name = "date")
	private LocalDate dateTime;
	
	/* Constructors --------------------------------------------------------------------------- */
	public FixedCosts () {}
	
	public FixedCosts(long fixedCost, String description, double value, LocalDate dateTime) {
		this.fixedCost = fixedCost;
		this.description = description;
		this.value = value;
		this.dateTime = dateTime;
	}
	
	public FixedCosts(long fixedCost, String description, double value) {
		this.fixedCost = fixedCost;
		this.description = description;
		this.value = value;
		this.dateTime = LocalDate.now();
	}
	/* Getters and Setters -------------------------------------------------------------------- */
	public long getFixedCost() {
		return fixedCost;
	}

	public void setFixedCost(long fixedCost) {
		this.fixedCost = fixedCost;
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

	public void setDateTime(LocalDate dateTime) {
		this.dateTime = dateTime;
	}
}

