package com.coffeelovers.pcu;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.coffeelovers.pcu.model.Product;
import com.coffeelovers.pcu.model.ProductRepository;

@SpringBootApplication
public class PcuApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcuApplication.class, args);
	}

	
	// Add some products to the database
	// Initial values
	@Bean
	ApplicationRunner init(ProductRepository productRepository) {
		return args -> {
			// Use save method to save the values on the database
			productRepository.save(new Product(123, 23, 1, "M"));
			productRepository.save(new Product(124, 10, 1, "S"));
			productRepository.save(new Product(125, 0.1, 1, "L"));
			
			// get all the records on terminal
			productRepository.findAll().forEach(System.out::println);
		};
	}
	
}
