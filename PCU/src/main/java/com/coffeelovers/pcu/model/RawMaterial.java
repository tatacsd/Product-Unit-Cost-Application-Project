package com.coffeelovers.pcu.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/***
 * this class  represents the supplier's raw materials
 */
@Entity
@Table(name = "raw_material")
public class RawMaterial {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long rawMaterialID;

	@Column(name = "invoiceNumber")
	private long invoiceNumber;
	
	@Column(name = "InvoiceID")
	private long invoiceID;
	
	@Column(name = "supplierID")
	private long supplierID;
	
	@Column(name = "dateTime")
	private Calendar dateTime;
	
	@Column(name = "notes")
	private String noteString;
	
	public RawMaterial() {}
	
	public RawMaterial(long rawMaterialID, long invoiceNumber, long invoiceID, long supplierID, String noteString) {
		this.rawMaterialID = rawMaterialID;
		this.invoiceNumber = invoiceNumber;
		this.invoiceID = invoiceID;
		this.supplierID = supplierID;
		this.noteString = noteString;
	}

	public long getRawMaterialID() {
		return rawMaterialID;
	}

	public void setRawMaterialID(long materialID) {
		this.rawMaterialID = materialID;
	}

	public long getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public long getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(long invoiceID) {
		this.invoiceID = invoiceID;
	}

	public long getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(long supplierID) {
		this.supplierID = supplierID;
	}

	public Calendar getDateTime() {
		return dateTime;
	}

	public void setDateTime() {
		this.dateTime = Calendar.getInstance();
	}

	public String getNoteString() {
		return noteString;
	}

	public void setNoteString(String noteString) {
		this.noteString = noteString;
	}
}
