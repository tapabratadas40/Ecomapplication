package com.myproduct.product_service.controller;

import java.util.ArrayList;
import java.util.List;

import com.myproduct.product_service.dto.ProductResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.myproduct.product_service.dto.ProductRequest;
import com.myproduct.product_service.entity.Product;
import com.myproduct.product_service.services.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@GetMapping("/allProducts")
	public  List<ProductResponce> getAllProducts(){

        return productService.getListOfProduct();
		
	}
	
	@PostMapping("/createProduct")
	@ResponseStatus(HttpStatus.CREATED)
	public void CreateProduct(@RequestBody ProductRequest productRequest) {

		productService.createProduct(productRequest);
	}

}
