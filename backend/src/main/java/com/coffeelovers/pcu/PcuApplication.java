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
			actorRepository.save(new Actor("Coffee Lovers","Sport Garment",123456789, "www.johndoe.com","admin@coffelovers.com","admin","Canada" ));			
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
			variableCostArray.push(new VariableCost("andrew",18)) ;
			variableCostArray.push(new VariableCost("Shanan",55)) ;
			variableCostArray.push(new VariableCost("canada",32)) ;


			Stack<RawMaterial> rawMaterialArray = new Stack<>();
			rawMaterialArray.push(new RawMaterial("Elastic Bands"));
			rawMaterialArray.push(new RawMaterial("Drawstring Cords"));
			rawMaterialArray.push(new RawMaterial("Flat Waxed Thread"));
			rawMaterialArray.push(new RawMaterial("Mandala Crafts Genuine Leathe"));

			Product p1 = new Product("CCS2205","Skrit","No_Pic","S - M - L - XL -XXL",100,200,300);
			Product p2 = new Product("AS225","T-shirt","No_Pic","M - L",200,300,500);
			Product p3 = new Product("cSD69","T-shirt","No_Pic","S - M - L - XL -XXL",500,500,1000);
			Product p4 = new Product("Cd963","Shirt","No_Pic","S - M ",400,300,700);
			Product p5 = new Product("AA005","jacket","No_Pic","S - M - L - XL -XXL",250,250,500);
			Product p6 = new Product("Vd682","jacket","No_Pic","S - M - L - XL -XXL",500,300,800);
			Product p7 = new Product("RF658","jacket","No_Pic"," XL -XXL",200,550,750);
			Product p8 = new Product("RF658","jacket","No_Pic","S - XL -XXL",1100,300,1400);
			Product p9 = new Product("RF658","jacket","No_Pic","S - M - L ",250,300,650);
			Product p10 = new Product("RF658","jacket","No_Pic"," M - L - XL -XXL",500,550,1050);



			for(int x = 0; x<variableCostArray.size();x++) {
				p1.addVaraibleCost(variableCostArray.get(x));
				p2.addVaraibleCost(variableCostArray.get(x));
				p3.addVaraibleCost(variableCostArray.get(x));
				p4.addVaraibleCost(variableCostArray.get(x));
				p5.addVaraibleCost(variableCostArray.get(x));
				p6.addVaraibleCost(variableCostArray.get(x));
				p7.addVaraibleCost(variableCostArray.get(x));
				p8.addVaraibleCost(variableCostArray.get(x));
				p9.addVaraibleCost(variableCostArray.get(x));
				p10.addVaraibleCost(variableCostArray.get(x));


			}

			
			for(int x = 0; x<rawMaterialArray.size();x++) {
				p1.addRawMaterials(rawMaterialArray.get(x));
				p2.addRawMaterials(rawMaterialArray.get(x));
				p3.addRawMaterials(rawMaterialArray.get(x));
				p4.addRawMaterials(rawMaterialArray.get(x));
				p5.addRawMaterials(rawMaterialArray.get(x));
				p6.addRawMaterials(rawMaterialArray.get(x));
				p7.addRawMaterials(rawMaterialArray.get(x));


			}

			
			productRepository.save(p1);
			productRepository.save(p2);
			productRepository.save(p3);
			productRepository.save(p4);
			productRepository.save(p5);
			productRepository.save(p6);
			productRepository.save(p7);

			productRepository.findAll().forEach(System.out::println);
						
		
			// invoice - invloc detals
			Stack<InvoiceDetails> invoiceDetails = new Stack<>();
			invoiceDetails.push( new InvoiceDetails(15,20,2,40,"no notes"));
			invoiceDetails.push( new InvoiceDetails(16,30,3,90,"no notes"));
			invoiceDetails.push( new InvoiceDetails(17,40,1,40,"no notes"));
			invoiceDetails.push( new InvoiceDetails(18,40,1,40,"no notes"));
			invoiceDetails.push( new InvoiceDetails(19,20,3,60,"no notes"));
			invoiceDetails.push( new InvoiceDetails(20,50,3,150,"no notes"));
			invoiceDetails.push( new InvoiceDetails(21,80,2,160,"no notes"));
			invoiceDetails.push( new InvoiceDetails(22,90,1,180,"no notes"));

			Invoice newInvoice1 = new Invoice("002",1,40);
			Invoice newInvoice2 = new Invoice("021",1,90);
			Invoice newInvoice3 = new Invoice("201",1,40);
			Invoice newInvoice4 = new Invoice("450",2,40);
			Invoice newInvoice5 = new Invoice("350",2,60);
			Invoice newInvoice6 = new Invoice("250",2,150);
			Invoice newInvoice7 = new Invoice("550",3,160);
			Invoice newInvoice8 = new Invoice("220",3,180);

			
								
			newInvoice1.addInvoiceDetails(invoiceDetails.get(0));
			newInvoice2.addInvoiceDetails(invoiceDetails.get(1));
			newInvoice3.addInvoiceDetails(invoiceDetails.get(2));
			newInvoice4.addInvoiceDetails(invoiceDetails.get(3));
			newInvoice5.addInvoiceDetails(invoiceDetails.get(4));
			newInvoice6.addInvoiceDetails(invoiceDetails.get(5));
			newInvoice7.addInvoiceDetails(invoiceDetails.get(6));
			newInvoice8.addInvoiceDetails(invoiceDetails.get(7));
				
		
			
			
			invoiceRepository.save(newInvoice1);
			invoiceRepository.save(newInvoice2);
			invoiceRepository.save(newInvoice3);
			invoiceRepository.save(newInvoice4);
			invoiceRepository.save(newInvoice5);
			invoiceRepository.save(newInvoice6);
			invoiceRepository.save(newInvoice7);
			invoiceRepository.save(newInvoice8);



	
	}
}