package com.coffeelovers.pcu.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coffeelovers.pcu.model.Product;
import com.coffeelovers.pcu.model.ProductRepository;
import com.coffeelovers.pcu.model.SupplierRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ReportController {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	SupplierRepository supplierRepository;
	
	/**
	 * this method return the final report
	 * @return
	 */
	@GetMapping("/report")
	public ResponseEntity<Object> getAllProduct(){
		Dictionary<String,Object> reportArray = new Hashtable<>();
		long productCount; 
		OptionalDouble productMaxValue; 
		OptionalDouble productMinValue;
		String productCostStandardDeviation ;
		OptionalDouble rawMaterialMaxValue; 
		OptionalDouble varaiableMaxValue; 

		
		try {
			// product part
				productCount = productRepository.findAll().stream().count();
				reportArray.put("number of products",productCount); // number of products
				reportArray.put("Avarage cost of products",productRepository.findAll().stream().mapToDouble(productValue -> productValue.getNetCost()).average());
				//
				productCostStandardDeviation = calculateSD(productRepository.findAll().stream().mapToDouble(productValue -> productValue.getNetCost()).sum(),
						productRepository.findAll().stream().map(v->v.getNetCost()).toArray());

				
				reportArray.put("Product Cost Standard Deviation",productCostStandardDeviation);

				productMaxValue = productRepository.findAll().stream().mapToDouble(productValue -> productValue.getNetCost()).max(); // the most expensive product
				reportArray.put("Most Expensive Product in term of Net value",productMaxValue);
				reportArray.put("Most Expensive Product in term of Net value-Code",productRepository.findAll().stream().filter(productValue ->productValue.getNetCost()== productMaxValue.getAsDouble()).findFirst().map(c->c.getCode()));// most  expensive  product's code
				//
				productMinValue = productRepository.findAll().stream().mapToDouble(productValue -> productValue.getNetCost()).min(); // the cheapest product
				reportArray.put("cheapest Product term of Net value",productMinValue);
				reportArray.put("cheapest Product term of Net value-Code",productRepository.findAll().stream().filter(productValue ->productValue.getNetCost()== productMinValue.getAsDouble()).findFirst().map(c->c.getCode()));// most  cheapest  product's code
				
				// most expensive product in term of raw materials
				rawMaterialMaxValue = productRepository.findAll().stream().mapToDouble(productValue -> productValue.getTotalMaterialCost()).min(); 
				reportArray.put("most expensive product in term of raw materials",rawMaterialMaxValue);
				reportArray.put("most expensive product in term of raw materials code",productRepository.findAll().stream().filter(productValue ->productValue.getTotalMaterialCost()== rawMaterialMaxValue.getAsDouble()).findFirst().map(c->c.getCode()));// most  cheapest  product's code
				// most cheapest product in term of variable cost
				varaiableMaxValue = productRepository.findAll().stream().mapToDouble(productValue -> productValue.getTotalvariableCost()).min(); 
				reportArray.put("The Cheapest product in term of Varaible Cost ",varaiableMaxValue);
				reportArray.put("The Cheapest product in term of Varaible Cost-Code ",productRepository.findAll().stream().filter(productValue ->productValue.getTotalvariableCost()== varaiableMaxValue.getAsDouble()).findFirst().map(c->c.getCode()));// most  cheapest  product's code
				
				// 
			// supplier's part
				reportArray.put("Number of Suppliers",supplierRepository.findAll().stream().count()); // number of suppliers
				
			
			return new ResponseEntity<>(reportArray, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	// this mothod calc the Standard Deviation of the product cost  
	public static String calculateSD(Double sum, Object[] costArray)
    {
		DecimalFormat df = new DecimalFormat("0.00");
				
		double standardDeviation = 0.0;
       
       double mean = sum/costArray.length;

        for(Object num: costArray) 
            standardDeviation += Math.pow((double)num - mean, 2);
        	

        return df.format(Math.sqrt(standardDeviation/costArray.length)) ;
    } 
}
