package com.coffeelovers.pcu.model;

/**
 * this class is to hold the report items
 * @author Andrew
 *
 */
public class Report {

	private String description;
	private Object value;
	
	public Object getDescrition() {
		return description;
	}
	public void setDescrition(String description) {
		this.description = description;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public Report(String descrition, Object value) {
		this.description = descrition;
		this.value = value;
	}
	
	
	

}
