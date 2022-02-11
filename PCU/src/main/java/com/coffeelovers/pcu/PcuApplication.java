package com.coffeelovers.pcu;

import javax.persistence.Column;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.coffeelovers.pcu.model.Actor;
import com.coffeelovers.pcu.model.ActorRepository;
import com.coffeelovers.pcu.model.Product;
import com.coffeelovers.pcu.model.ProductRepository;
import com.coffeelovers.pcu.model.RawMaterialRepository;
import com.coffeelovers.pcu.model.Supplier;
import com.coffeelovers.pcu.model.SupplierRepository;

@SpringBootApplication
public class PcuApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcuApplication.class, args);
	}

	@Bean
	ApplicationRunner init(ProductRepository productRepository, SupplierRepository supplierRepository,ActorRepository actorRepository,RawMaterialRepository rawMaterialRepository) {
		return args -> {
			
			
			actorRepository.save(new Actor("Zara", "kids clothing", 6047788,"Zara@gmail.com", "www.zara.com","2225","Canada"));
			actorRepository.save(new Actor("H&M", "Women clothing", 6047788,"Handm@gmail.com", "www.zara.com","2225","Canada"));
			actorRepository.save(new Actor("Masimodotti", "men clothing", 6047788,"Masimodotti@gmail.com", "www.zara.com","2225","Canada"));
			actorRepository.findAll().forEach(System.out::println);

			supplierRepository.save(new Supplier("Andrew", "Shanan", 6047788,"Andrew@gmail.com", "Canada"));
			supplierRepository.save(new Supplier("Chelsy", "Chelsy", 6043366,"Chelsy@gmail.com", "India"));
			supplierRepository.save(new Supplier("Father", "son", 6045050,"Shanan@gmail.com", "USA"));
			supplierRepository.findAll().forEach(System.out::println);
			
			productRepository.save(new Product(123, 23, "https", "M"));
			productRepository.save(new Product(124, 10, "https", "S"));
			productRepository.save(new Product(125, 0.1, "https", "L"));
			productRepository.findAll().forEach(System.out::println);
			



		};
	}
	
}
