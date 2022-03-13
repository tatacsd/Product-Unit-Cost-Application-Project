package com.coffeelovers.pcu.model;

import javax.persistence.*;

/***
 * this class represents the raw materials' invoices
 */
@Entity
@Table(name ="invoices")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "invoiceNumber")
	private String invoiceNumber;
	
	@Column(name = "supplier")
	private long supplierID;
	
	@Column(name = "invoiceValue")
	private double invoiceValue;
	
	@Column(name = "invoiceDate")
	private String invoiceDate;

	public Invoice() {}
	
	/***
	 * 
	 * @param invoiceNumber
	 * @param supplierID
	 * @param invoiceValue
	 * @param invoiceDate
	 */
	public Invoice(String invoiceNumber, long supplierID, double invoiceValue, String invoiceDate) {
		this.invoiceNumber = invoiceNumber;
		this.supplierID = supplierID;
		this.invoiceValue = invoiceValue;
		this.invoiceDate = invoiceDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public long getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(long supplierID) {
		this.supplierID = supplierID;
	}

	public double getInvoiceValue() {
		return invoiceValue;
	}

	public void setInvoiceValue(double invoiceValue) {
		this.invoiceValue = invoiceValue;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", invoiceNumber=" + invoiceNumber + ", supplierID=" + supplierID
				+ ", invoiceValue=" + invoiceValue + ", invoiceDate=" + invoiceDate + "]";
	}
}