package com.coffeelovers.pcu.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RawMaterialRepository extends JpaRepository<RawMaterial,Long> {
	List<RawMaterial> findByMaterialID (long materialID);
	List<RawMaterial> findByInvoiceNumber (long invoiceNumber);
	List<RawMaterial> findBySupplierID (long supplierID);
}
