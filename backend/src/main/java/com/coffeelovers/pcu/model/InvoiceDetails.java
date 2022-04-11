package com.coffeelovers.pcu.model;

import java.time.LocalDate;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

/***
 * this class represents the invoice  raw materials datils
 * we do not need to have SupplierId because we have already the invoiceID that belongs to supplier
 */
@Entity
@Table(name = "invoice_detail")
public class InvoiceDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long InvoiceDetailsID;
	

	@Column(name = "raw_material_id")
	private long rawMaterialID;
	
	@Column(name = "value")
	private double value;
	
	@Column(name = "quantity")
	private double quantity;
	
	@Column(name = "total_value")
	private double totalValue;
	
	
	@Column(name = "dateTime")
	private LocalDate dateTime;
	
	@Column(name = "notes")
	private String noteString;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name = "invoiceId", nullable = false) // you can chose any name for the the id
	@JsonIgnore
	private Invoice invoice;

	public InvoiceDetails() {}


	
	public InvoiceDetails(long rawMaterialID, double value, double quantity, double totalValue,
			 String noteString) {
		this.rawMaterialID = rawMaterialID;
		this.value = value;
		this.quantity = quantity;
		this.totalValue = totalValue;
		this.dateTime = LocalDate.now();
		this.noteString = noteString;
	}




	public long getInvoiceDetailsID() {
		return InvoiceDetailsID;
	}


	public void setInvoiceDetailsID(long invoiceDetailsID) {
		InvoiceDetailsID = invoiceDetailsID;
	}




	public long getRawMaterialID() {
		return rawMaterialID;
	}


	public void setRawMaterialID(long rawMaterialID) {
		this.rawMaterialID = rawMaterialID;
	}


	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}


	public double getQuantity() {
		return quantity;
	}


	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}


	public double getTotalValue() {
		return totalValue;
	}


	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}


	public LocalDate getDateTime() {
		return dateTime;
	}


	public void setDateTime(LocalDate dateTime) {
		this.dateTime = dateTime;
	}


	public String getNoteString() {
		return noteString;
	}


	public void setNoteString(String noteString) {
		this.noteString = noteString;
	}




	public Invoice getInvoice() {
		return invoice;
	}



	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}





	
	
	






	
	
	
	
	

	
}