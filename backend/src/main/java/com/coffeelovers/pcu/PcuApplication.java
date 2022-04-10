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

			
			//-1 Actor
			actorRepository.save(new Actor("Thays Company","Sport Garment",559594, "www.Thays.com","login@Thays.com","login","canada" ));			
			actorRepository.findAll().forEach(System.out::println);
		
			//2- suppliers
			supplierRepository.save(new Supplier("Andrew","shanan",604111111,"shanan@gmail.com","Canada"));	
			supplierRepository.save(new Supplier("Igor","Koshefemko",604999999,"shanan@gmail.com","Canada"));			
			supplierRepository.save(new Supplier("Luan","Luan",604789987,"Luan@gmail.com","Canada"));			
			supplierRepository.findAll().forEach(System.out::println);
			
			//3 VariableCost
			variableCostsRepository.save(new VariableCost("Rent",18));
			variableCostsRepository.save(new VariableCost("Direct Labor",35));
			variableCostsRepository.save(new VariableCost("Utility Costs",12));
			variableCostsRepository.save(new VariableCost("Insurance",1));
			variableCostsRepository.save(new VariableCost("Office Supplies",2));
			variableCostsRepository.findAll().forEach(System.out::println);
			
			//4 VariableCost
			variableCostsRepository.save(new VariableCost("Rent",18));
			variableCostsRepository.save(new VariableCost("Direct Labor",35));
			variableCostsRepository.save(new VariableCost("Utility Costs",12));
			variableCostsRepository.save(new VariableCost("Insurance",1));
			variableCostsRepository.save(new VariableCost("Office Supplies",2));
			variableCostsRepository.findAll().forEach(System.out::println);
			
			//5- rowMaterial
			rowMaterialRepository.save(new RawMaterial("Zipper"));
			rowMaterialRepository.save(new RawMaterial("Button"));
			rowMaterialRepository.save(new RawMaterial("Silk Fabric"));
			rowMaterialRepository.save(new RawMaterial("cotton Fabric"));
			rowMaterialRepository.save(new RawMaterial("polyester fabric"));
			rowMaterialRepository.save(new RawMaterial("Cotton Twine string"));
			rowMaterialRepository.save(new RawMaterial("CiaraQ Sewing Threads"));
			rowMaterialRepository.save(new RawMaterial("Luxbon Sewing Threads"));
			rowMaterialRepository.save(new RawMaterial("Elastic Bands"));
			rowMaterialRepository.save(new RawMaterial("Drawstring Cords"));
			rowMaterialRepository.save(new RawMaterial("Flat Waxed Thread"));
			rowMaterialRepository.save(new RawMaterial("Mandala Crafts Genuine Leathe"));
			rowMaterialRepository.findAll().forEach(System.out::println);
			
			// product - variableCost: M-N
		
			Stack<VariableCost> variableCostArray = new Stack<>();
			variableCostArray.push(new VariableCost("Rent",18)) ;
			variableCostArray.push(new VariableCost("Labor",55)) ;
			variableCostArray.push(new VariableCost("Utility",32)) ;

			Stack<RawMaterial> rawMaterialArray = new Stack<>();
			rawMaterialArray.push(new RawMaterial("Elastic Bands"));
			rawMaterialArray.push(new RawMaterial("Drawstring Cords"));
			rawMaterialArray.push(new RawMaterial("Flat Waxed Thread"));
			rawMaterialArray.push(new RawMaterial("Mandala Crafts Genuine Leathe"));

			Product p1 = new Product("CCS2205","Skrit","No_Pic","S - M - L - XL -XXL");
			
			
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
			invoiceDetails.push( new InvoiceDetails(1,20,2,5000,"no notes"));
			invoiceDetails.push( new InvoiceDetails(2,30,3,9000,"no notes"));
			invoiceDetails.push( new InvoiceDetails(3,40,1,1000,"no notes"));
			invoiceDetails.push( new InvoiceDetails(4,40,1,1000,"no notes"));
			invoiceDetails.push( new InvoiceDetails(5,40,1,1000,"no notes"));
			invoiceDetails.push( new InvoiceDetails(6,40,1,1000,"no notes"));
			invoiceDetails.push( new InvoiceDetails(7,40,1,1000,"no notes"));
			invoiceDetails.push( new InvoiceDetails(8,40,1,1000,"no notes"));

			Invoice newInvoice = new Invoice("2S25",1,15000);


			while(!invoiceDetails.isEmpty())
				newInvoice.addInvoiceDetails(invoiceDetails.pop());
			
			invoiceRepository.save(newInvoice);
			

			
					



	
	}
}