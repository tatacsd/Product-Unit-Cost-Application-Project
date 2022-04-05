package com.coffeelovers.pcu;

import java.util.*;

//import org.graalvm.compiler.lir.Variable;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.coffeelovers.pcu.model.Actor;
import com.coffeelovers.pcu.model.ActorRepository;

import com.coffeelovers.pcu.model.Invoice;
import com.coffeelovers.pcu.model.InvoiceRepository;
import com.coffeelovers.pcu.model.Product;
import com.coffeelovers.pcu.model.ProductRepository;
import com.coffeelovers.pcu.model.RawMaterial;
import com.coffeelovers.pcu.model.RawMaterialRepository;
import com.coffeelovers.pcu.model.InvoiceDetails;
import com.coffeelovers.pcu.model.InvoiceDetailsRepository;
import com.coffeelovers.pcu.model.Supplier;
import com.coffeelovers.pcu.model.SupplierRepository;
import com.coffeelovers.pcu.model.VariableCost;
import com.coffeelovers.pcu.model.VariableCostsRepository;

@SpringBootApplication
public class PcuApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcuApplication.class, args);
	}

	@Bean
	ApplicationRunner init(
			ActorRepository actorRepository,
			ProductRepository productRepository, 
			SupplierRepository supplierRepository, 
			VariableCostsRepository variableCostsRepository, 
			InvoiceRepository invoiceRepository,
			InvoiceDetailsRepository invoiceDetailsRepository,
			RawMaterialRepository rowMaterialRepository
			) {
		return args -> {
			
			//PopulateNoRalationalDataBase(actorRepository,productRepository,supplierRepository,variableCostsRepository,invoiceRepository,invoiceDetailsRepository,rowMaterialRepository);
			PopulateRalationalDataBase(actorRepository,productRepository,supplierRepository,variableCostsRepository,invoiceRepository,invoiceDetailsRepository,rowMaterialRepository);

		};
	}
	
	/***
	 * taster without M-N or 1-m
	 * @param actorRepository
	 * @param productRepository
	 * @param supplierRepository
	 * @param variableCostsRepository
	 * @param invoiceRepository
	 * @param invoiceDetailsRepository
	 * @param rowMaterialRepository
	 */
	private void PopulateNoRalationalDataBase(ActorRepository actorRepository,
			ProductRepository productRepository, 
			SupplierRepository supplierRepository, 
			VariableCostsRepository variableCostsRepository, 
			InvoiceRepository invoiceRepository,
			InvoiceDetailsRepository invoiceDetailsRepository,
			RawMaterialRepository rowMaterialRepository) {

		
		//-1 Actor
		actorRepository.save(new Actor("Modlina","Women Fashion",559594, "www.shanan.com","shanan@gmail.com","ddd","canada" ));			
		actorRepository.findAll().forEach(System.out::println);
		
		//2- product
		productRepository.save(new Product("CCS2205","Skrit","ddd","222-220"));			
		productRepository.findAll().forEach(System.out::println);
		
		
		//3- suppliers
		supplierRepository.save(new Supplier("Andrew","shanan",5559594,"Snana@gmail.com","Syria"));			
		supplierRepository.findAll().forEach(System.out::println);
		
		
		//4- VariableCost
		variableCostsRepository.save(new VariableCost("Rent",25.5));			
		variableCostsRepository.findAll().forEach(System.out::println);
		
		//5- invoice
		invoiceRepository.save(new Invoice("dd",10,8500));
		invoiceRepository.findAll().forEach(System.out::println);
		
		//6- invoiceDetails
		//invoiceDetailsRepository.save();
		invoiceDetailsRepository.findAll().forEach(System.out::println);
		
		//6- rowMaterial
		rowMaterialRepository.save(new RawMaterial("Zipper"));
		rowMaterialRepository.findAll().forEach(System.out::println);
	}

	 /* taster with M-N
	 * @param actorRepository
	 * @param productRepository
	 * @param supplierRepository
	 * @param variableCostsRepository
	 * @param invoiceRepository
	 * @param invoiceDetailsRepository
	 * @param rowMaterialRepository
	 */
	private void PopulateRalationalDataBase(ActorRepository actorRepository,
			ProductRepository productRepository, 
			SupplierRepository supplierRepository, 
			VariableCostsRepository variableCostsRepository, 
			InvoiceRepository invoiceRepository,
			InvoiceDetailsRepository invoiceDetailsRepository,
			RawMaterialRepository rowMaterialRepository) {


			// product - variableCost: M-N
		
			Stack<VariableCost> variableCostArray = new Stack<>();
			
			variableCostArray.push(new VariableCost("coc",2500)) ;
			variableCostArray.push(new VariableCost("lo",2500)) ;
			variableCostArray.push(new VariableCost("zo",2500)) ;

			Stack<RawMaterial> rawMaterialArray = new Stack<>();
			rawMaterialArray.push(new RawMaterial("Zippers"));
			rawMaterialArray.push(new RawMaterial("Zippers"));

			Product p1 = new Product("CCS2205","Skrit","ddd","222-220");
			
			
			while(!variableCostArray.isEmpty())
				p1.addVaraibleCost(variableCostArray.pop());

			while(!rawMaterialArray.isEmpty())
				p1.addRawMaterials(rawMaterialArray.pop());

			


			productRepository.save(p1);
			productRepository.findAll().forEach(System.out::println);
						
		
				/* note1: - no need to save the leader class's objects(that has the realtion Creation becouse the dependent class will save the record in both tables)
				
						  - in the leader  class(that hold the relational table), no need to add any additional method more than set and get.....
						  
						  - in the dependen class, we must create a class to pupulate the set array for both class.
				*/
		
			// invoice - invlice detals
			Stack<InvoiceDetails> invoiceDetails = new Stack<>();
			invoiceDetails.push( new InvoiceDetails(2,2500,2,5000,"no notes"));
			invoiceDetails.push( new InvoiceDetails(1,3000,3,9000,"no notes"));
			invoiceDetails.push( new InvoiceDetails(3,1000,1,1000,"no notes"));
			
			Invoice newInvoice = new Invoice("2S25",1,15000);
			while(!invoiceDetails.isEmpty())
				newInvoice.addInvoiceDetails(invoiceDetails.pop());
			
			invoiceRepository.save(newInvoice);
			

			
					



	
	}
}