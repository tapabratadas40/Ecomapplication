package com.myproduct.product_service.controller;

import java.util.List;
import com.myproduct.product_service.dto.ProductResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.myproduct.product_service.dto.ProductRequest;
import com.myproduct.product_service.services.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@GetMapping("/products")
	public ResponseEntity<List<ProductResponce>> getAllProducts(){

        return new ResponseEntity<>(productService.getListOfProduct(),HttpStatus.OK);
		
	}
	
	@PostMapping("/products")
	public ResponseEntity<?> CreateProduct(@RequestBody ProductRequest productRequest) {
		return new ResponseEntity<>(productService.createProduct(productRequest),HttpStatus.CREATED);
	}

	@PostMapping("/{id}/products")
	public ResponseEntity<ProductResponce> CreateProduct(@PathVariable String id,@RequestBody ProductRequest productRequest) {

		return new ResponseEntity<>(productService.updateProduct(id, productRequest),HttpStatus.OK);
	}

	@GetMapping("/{id}/products")
	public ResponseEntity<ProductResponce> getProductByProductId(@PathVariable String id){
		return new ResponseEntity<>(productService.getProductByProductId(id),HttpStatus.OK);
	}

	@DeleteMapping("/{id}/products")
	public String deleteProduct(@PathVariable String id){

		productService.deleteProduct(id);
		return "Product Deleted";
	}

}
