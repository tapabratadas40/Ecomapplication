package com.myproduct.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myproduct.product_service.dto.ProductRequest;
import com.myproduct.product_service.entity.Product;
import com.myproduct.product_service.services.ProductService;

@RestController("/api/v1")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@GetMapping("/allProducts")
	public  List<Product> getAllProducts(){
		
		return productService.getListOfProduct();
		
	}
	
	@PostMapping("/createProduct")
	@ResponseStatus(HttpStatus.CREATED)
	public void CreateProduct(@RequestBody ProductRequest productRequest) {

		productService.createProduct(productRequest);
	}

}
