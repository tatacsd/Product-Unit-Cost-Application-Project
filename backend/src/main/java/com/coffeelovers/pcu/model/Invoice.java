package com.coffeelovers.pcu.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/***
 * this class represents the raw materials' invoices
 */
@Entity
@Table(name ="invoices")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long invliceId;
	
	@Column(name = "invoiceNumber")
	private String invoiceNumber;
	
	@Column(name = "supplier")
	private long supplierID;
	
	@Column(name = "invoiceValue")
	private double invoiceValue;
	
	@Column(name = "invoiceDate")
	private LocalDate invoiceDate;

	
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<InvoiceDetails> InvoiceDetails = new HashSet<>();
	
	public Invoice() {}
	
	/***
	 * 
	 * @param invoiceNumber
	 * @param supplierID
	 * @param invoiceValue
	 */
	public Invoice(String invoiceNumber, long supplierID, double invoiceValue) {
		this.invoiceNumber = invoiceNumber;
		this.supplierID = supplierID;
		this.invoiceValue = invoiceValue;
		this.invoiceDate = LocalDate.now();
	}


	
	public long getInvliceId() {
		return invliceId;
	}

	public void setInvliceId(long invliceId) {
		this.invliceId = invliceId;
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

	public LocalDate getInvoiceDate() {
		return LocalDate.now();
	}

	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = LocalDate.now();
	}

	public Set<InvoiceDetails> getInvoiceDetails() {
		return InvoiceDetails;
	}

	public void setInvoiceDetails(Set<InvoiceDetails> invoiceDetails) {
		InvoiceDetails = invoiceDetails;
	}

	public void addInvoiceDetails(InvoiceDetails invoiceDetails) {
		this.InvoiceDetails.add(invoiceDetails);
		invoiceDetails.setInvoice(this);
	}
	
	



}