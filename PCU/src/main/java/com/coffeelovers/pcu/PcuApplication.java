package com.coffeelovers.pcu;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.coffeelovers.pcu.model.Actor;
import com.coffeelovers.pcu.model.ActorRepository;
import com.coffeelovers.pcu.model.Product;
import com.coffeelovers.pcu.model.ProductRepository;
import com.coffeelovers.pcu.model.Supplier;
import com.coffeelovers.pcu.model.SupplierRepository;
import com.coffeelovers.pcu.model.VariableCosts;
import com.coffeelovers.pcu.model.VariableCostsRepository;

@SpringBootApplication
public class PcuApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcuApplication.class, args);
	}

	@Bean
	ApplicationRunner init(ProductRepository productRepository, SupplierRepository supplierRepository, ActorRepository actorRepository, VariableCostsRepository variableCostsRepository) {
		return args -> {
			
			
			actorRepository.save(new Actor("Zara", "kids clothing", 6047788,"Zara@gmail.com", "www.zara.com","2225","Canada"));
			actorRepository.save(new Actor("H&M", "Women clothing", 6047788,"Handm@gmail.com", "www.zara.com","2225","Canada"));
			actorRepository.save(new Actor("Masimodotti", "men clothing", 6047788,"Masimodotti@gmail.com", "www.zara.com","2225","Canada"));
			actorRepository.findAll().forEach(System.out::println);

			supplierRepository.save(new Supplier("Andrew", "Shanan", 6047788,"Andrew@gmail.com", "Canada"));
			supplierRepository.save(new Supplier("Chelsy", "Chelsy", 6043366,"Chelsy@gmail.com", "India"));
			supplierRepository.save(new Supplier("Father", "son", 6045050,"Shanan@gmail.com", "USA"));
			supplierRepository.findAll().forEach(System.out::println);
			
			productRepository.save(new Product( 23, "https", "M"));
			productRepository.save(new Product( 10, "https", "S"));
			productRepository.save(new Product(0.1, "https", "L"));
			productRepository.findAll().forEach(System.out::println);
			
			variableCostsRepository.save(new VariableCosts("Electricity", 100, "2/11/2020"));
			variableCostsRepository.save(new VariableCosts("Gas",200,"2/05/2020"));
			variableCostsRepository.save(new VariableCosts("Water",500,"2/07/2020"));
			
		};
	}
	
}
