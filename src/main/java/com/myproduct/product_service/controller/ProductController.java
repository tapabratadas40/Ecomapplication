package com.myproduct.product_service.controller;

import java.util.List;
import com.myproduct.product_service.dto.ProductResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.myproduct.product_service.dto.ProductRequest;
import com.myproduct.product_service.services.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@GetMapping("/products")
	public  List<ProductResponce> getAllProducts(){

        return productService.getListOfProduct();
		
	}
	
	@PostMapping("/products")
	@ResponseStatus(HttpStatus.CREATED)
	public void CreateProduct(@RequestBody ProductRequest productRequest) {

		productService.createProduct(productRequest);
	}

	@PostMapping("/{id}/products")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ProductResponce CreateProduct(@PathVariable String id,@RequestBody ProductRequest productRequest) {

		return productService.updateProduct(id, productRequest);
	}

	@GetMapping("/{id}/products")
	public ProductResponce getProductByProductId(@PathVariable String id){
		return productService.getProductByProductId(id);
	}

	@DeleteMapping("/{id}/products")
	public String deleteProduct(@PathVariable String id){

		productService.deleteProduct(id);
		return "Product Deleted";
	}

}
