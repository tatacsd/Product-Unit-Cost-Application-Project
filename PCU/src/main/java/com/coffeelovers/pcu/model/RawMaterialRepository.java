package com.coffeelovers.pcu.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {
	List<RawMaterial> findByRawMaterialID (Long rawMaterialID);
	List<RawMaterial> findByInvoiceNumber (Long invoiceNumber);
	List<RawMaterial> findBySupplierID (Long supplierID);
}
