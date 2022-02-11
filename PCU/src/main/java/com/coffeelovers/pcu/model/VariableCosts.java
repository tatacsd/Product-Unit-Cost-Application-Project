package com.coffeelovers.pcu.model;

import java.util.Date;

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
	private long id;
	
	@Column(name = "Discription")
	private String discription;
	
	@Column(name = "Value")
	private int value;
	
	
	
	@Column(name="Date&Time")
	private String dateAndTime;
	
	public VariableCosts() {
		
	}
	
	public VariableCosts(String discription, int value, String dateAndTime) {
		this.discription = discription;
		this.value = value;
		this.dateAndTime = dateAndTime;
	}
	
	public long getId() {
		return id;
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
