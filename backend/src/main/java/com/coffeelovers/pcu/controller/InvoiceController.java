package com.coffeelovers.pcu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffeelovers.pcu.model.Invoice;
import com.coffeelovers.pcu.model.InvoiceDetails;
import com.coffeelovers.pcu.model.InvoiceRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class InvoiceController {

	@Autowired
	InvoiceRepository invoiceRepository;

	@GetMapping("/invoices/{id}")
	public ResponseEntity<Invoice> getInvoiceId(@PathVariable("id") long invoiceID) {
		try {
			Optional<Invoice> invoiceData = invoiceRepository.findById(invoiceID);
			if (invoiceData.isPresent())
				return new ResponseEntity<>(invoiceData.get(), HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/invoices")
	public ResponseEntity<List<Invoice>> getAllInvoice() {
		try {
			List<Invoice> invoiceArray = new ArrayList<Invoice>();
			invoiceRepository.findAll().forEach(invoiceArray::add);
			return new ResponseEntity<>(invoiceArray, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/invoices/{id}")
	public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") long invoiceID, @RequestBody Invoice invoice) {
		try {
			Optional<Invoice> invoiceData = invoiceRepository.findById(invoiceID);
			if (invoiceData.isPresent()) {
				Invoice _invoice = invoiceData.get();

				if (invoice.getInvoiceNumber() != null)
					_invoice.setInvoiceNumber(invoice.getInvoiceNumber());

				if (invoice.getSupplierID() != 0)
					_invoice.setSupplierID(invoice.getSupplierID());

				if (invoice.getInvoiceValue() != 0)
					_invoice.setInvoiceValue(invoice.getInvoiceValue());

				if (invoice.getInvoiceDate() != null)
					_invoice.setInvoiceDate(invoice.getInvoiceDate());

				return new ResponseEntity<>(invoiceRepository.save(_invoice), HttpStatus.OK);
			} else
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/invoices")
	public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
		try {
			return new ResponseEntity<>(invoiceRepository.save(invoice), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/invoices/{id}/details")
	public ResponseEntity<Invoice> createInvoiceDetails(@PathVariable("id") long invoiceID, @RequestBody InvoiceDetails invoicedetails) {
		try {
			// find by id
			Optional<Invoice> invoiceData = invoiceRepository.findById(invoiceID);
			
			// add the invoice data to the invoice if exist
			if (invoiceData.isPresent()) {
				Invoice _invoice = invoiceData.get();
				InvoiceDetails _invoiceDetails = new InvoiceDetails();
				
				if(invoicedetails.getRawMaterialID() != 0) {
					_invoiceDetails.setRawMaterialID(invoicedetails.getRawMaterialID());
				}
				
				if(invoicedetails.getValue() != 0) {
					_invoiceDetails.setValue(invoicedetails.getValue());
				}
				
				if(invoicedetails.getQuantity() != 0) {
					_invoiceDetails.setQuantity(invoicedetails.getQuantity());
				}
				
				if(invoicedetails.getTotalValue() != 0) {
					_invoiceDetails.setTotalValue(invoicedetails.getTotalValue());
				}
				
				if(invoicedetails.getDateTime() != null) {
					_invoiceDetails.setDateTime(invoicedetails.getDateTime());
				}
				
				if(invoicedetails.getNoteString() != null) {
					_invoiceDetails.setNoteString(invoicedetails.getNoteString());
				}
								
				
				_invoice.addInvoiceDetails(_invoiceDetails);
				
				
				return new ResponseEntity<>(invoiceRepository.save(_invoice), HttpStatus.CREATED);
			}
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping("/invoices/{id}")
	public ResponseEntity<HttpStatus> deleteInvoice(@PathVariable("id") long invoiceID) {
		try {
			invoiceRepository.deleteById(invoiceID);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
