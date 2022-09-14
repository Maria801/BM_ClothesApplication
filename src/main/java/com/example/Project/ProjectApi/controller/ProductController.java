package com.example.Project.ProjectApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project.ProjectApi.classes.EnterData;
import com.example.Project.ProjectApi.classes.HomeProducts;
import com.example.Project.ProjectApi.classes.ReturnProduct;
import com.example.Project.ProjectApi.entity.Product;
import com.example.Project.ProjectApi.entity.ProductDetails;
import com.example.Project.ProjectApi.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
 
	@Autowired
	ProductService productService;
	
	
	@DeleteMapping(value= {"/deleteItem/{id}"})
	public void deleteItem(@PathVariable int id) {
		productService.deleteItemFromProduct(id);	
	}
	
	@PostMapping(value= {"/addItem"})
	public void addItem(@RequestBody Product product) {
		productService.addItem(product);	
	}
	
	 @GetMapping(value= {"/getProduct/{productId}/{userId}"})
	    public ReturnProduct getProductById(@PathVariable int productId, @PathVariable int userId) {
	    	return productService.getProductById(productId,userId);
	    }
	 
	 @GetMapping(value= {"/getAllProducts/{userId}"})
	 public List<HomeProducts> getAllProducts(@PathVariable int userId){
		 return productService.getAllProducts(userId);
	 }
	 
	 @PostMapping(value= {"/addColorOrSize"})
	 public void addColorOrSize(@RequestBody EnterData enterData) {
		 productService.addColorOrSize(enterData);
	 }
	 
	 @GetMapping (value= {"/getProductBycolor/{userId}/{productId}/{chosenColor}"})
	 public ReturnProduct returnBycolor(@PathVariable int userId, @PathVariable int productId,@PathVariable String chosenColor) {
		return  productService.returnBycolor(userId, productId,chosenColor);
	 }
		
}
