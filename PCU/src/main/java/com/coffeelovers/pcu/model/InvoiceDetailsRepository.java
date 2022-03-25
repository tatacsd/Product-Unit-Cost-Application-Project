package com.coffeelovers.pcu.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails, Long> {
	List<InvoiceDetails> findByInvoiceDetailsID (Long invoiceDetailsID);
}