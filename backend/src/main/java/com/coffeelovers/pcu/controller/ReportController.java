package com.coffeelovers.pcu.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.coffeelovers.pcu.model.Report;
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
		List<Report> reportArray = new ArrayList<>();
		
		long productCount; 
		Object productMaxValue; 
		Object productMinValue;
		Object productCostStandardDeviation ;
		Object productCostMedian ;
		Object rawMaterialMaxValue; 
		Object varaiableMaxValue; 

		
		try {
			// product part
				productCount = productRepository.findAll().stream().count();
				reportArray.add(new Report("Number of products",productCount)); // number of products
				reportArray.add(new Report("Avarage cost of products", productRepository.findAll().stream().mapToDouble(productValue -> productValue.getNetCost()).average()));
				//
				productCostStandardDeviation = calculateSD(productRepository.findAll().stream().mapToDouble(productValue -> productValue.getNetCost()).sum(),
						productRepository.findAll().stream().map(v->v.getNetCost()).toArray());
				reportArray.add(new Report("Product Cost Standard Deviation",productCostStandardDeviation));
				
				//
				productCostMedian = getMedian(productRepository.findAll().stream().map(v->v.getNetCost()).toArray());
				reportArray.add(new Report("Product Cost Median",productCostMedian));

				//
				reportArray.add(new Report("Product Cost Virance",Math.pow(Double.parseDouble((String) productCostStandardDeviation) ,2)));

				//
				productMaxValue = productRepository.findAll().stream().mapToDouble(productValue -> productValue.getNetCost()).max(); // the most expensive product
				
				reportArray.add(new Report("Most Expensive Product in term of Net value",productMaxValue));
				reportArray.add(new Report("Most Expensive Product in term of Net value-Code",productRepository.findAll().stream().filter(productValue ->productValue.getNetCost()== ((OptionalDouble) productMaxValue).getAsDouble()).findFirst().map(c->c.getCode())));// most  expensive  product's code
				//
				productMinValue = productRepository.findAll().stream().mapToDouble(productValue -> productValue.getNetCost()).min(); // the cheapest product
				reportArray.add(new Report("Cheapest Product in term of Net value",productMinValue));
				reportArray.add(new Report("Cheapest Product in term of Net value-Code",productRepository.findAll().stream().filter(productValue ->productValue.getNetCost()== ((OptionalDouble) productMinValue).getAsDouble()).findFirst().map(c->c.getCode())));// most  cheapest  product's code
				
				// most expensive product in term of raw materials
				rawMaterialMaxValue = productRepository.findAll().stream().mapToDouble(productValue -> productValue.getTotalMaterialCost()).min(); 
				reportArray.add(new Report("Most expensive product in term of raw materials",rawMaterialMaxValue));
				reportArray.add(new Report("Most expensive product in term of raw materials code",productRepository.findAll().stream().filter(productValue ->productValue.getTotalMaterialCost()== ((OptionalDouble) rawMaterialMaxValue).getAsDouble()).findFirst().map(c->c.getCode())));// most  cheapest  product's code
				// most cheapest product in term of variable cost
				varaiableMaxValue = productRepository.findAll().stream().mapToDouble(productValue -> productValue.getTotalvariableCost()).min(); 
				reportArray.add(new Report("The Cheapest product in term of Varaible Cost ",varaiableMaxValue));
				reportArray.add(new Report("The Cheapest product in term of Varaible Cost-Code ",productRepository.findAll().stream().filter(productValue ->productValue.getTotalvariableCost()== ((OptionalDouble) varaiableMaxValue).getAsDouble()).findFirst().map(c->c.getCode())));// most  cheapest  product's code
				
				// 
			// supplier's part
				reportArray.add(new Report("Number of Suppliers",supplierRepository.findAll().stream().count())); // number of suppliers
				
			
			return new ResponseEntity<>(reportArray, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	// calculation median
	public static double getMedian(Object[] costArray)
    {
        // First we sort the array
		int n= costArray.length;
		double costElements[] = new double[n];

		for(int x = 0; x<costElements.length;x++)
			costElements[x] = (double)costArray[x];

        Arrays.sort(costElements);
		
        // check for even case
        if (n % 2 != 0)
            return (double)costElements[n / 2];
 
        return (double)(costElements[(n - 1) / 2] + costElements[n / 2]) / 2.0;
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
