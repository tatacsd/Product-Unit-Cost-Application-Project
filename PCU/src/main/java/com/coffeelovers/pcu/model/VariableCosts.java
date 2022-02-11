package com.coffeelovers.pcu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VariableCosts")
public class VariableCosts {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long variableCostsID;
	
	@Column(name = "discription")
	private String discription;
	
	@Column(name = "value")
	private int value;
	
	@Column(name="dateTime")
	private String dateAndTime;
	
	public VariableCosts() {
		
	}

	public VariableCosts(String discription, int value, String dateAndTime) {
		this.discription = discription;
		this.value = value;
		this.dateAndTime = dateAndTime;
	}

	public long getVariableCostsID() {
		return variableCostsID;
	}

	public void setVariableCostsID(long variableCostsID) {
		this.variableCostsID = variableCostsID;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	
	
	
	
	
}
